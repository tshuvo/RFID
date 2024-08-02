package com.pakiza.fortiz.rfid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.api.RetrofitClient;
import com.pakiza.fortiz.rfid.common.CommonService;
import com.pakiza.fortiz.rfid.common.LoadingDialog;
import com.pakiza.fortiz.rfid.controller.CustomSpinnerAdapter;
import com.pakiza.fortiz.rfid.controller.CustomWarehouseErrorDataAdapter;
import com.pakiza.fortiz.rfid.controller.StringUtils;
import com.pakiza.fortiz.rfid.model.ErrorRfidStatusResponse;
import com.pakiza.fortiz.rfid.model.ErrorRfidStatusWarehouseBody;
import com.pakiza.fortiz.rfid.model.SaveWarehouseRfidBody;
import com.pakiza.fortiz.rfid.model.UhfInfo;
import com.pakiza.fortiz.rfid.model.WareHouseWiseDataResponseModel;
import com.pakiza.fortiz.rfid.model.WarehouseListResponse;
import com.pakiza.fortiz.rfid.model.WarehouseRfidSaveResponse;
import com.rscja.deviceapi.RFIDWithUHFUART;
import com.rscja.deviceapi.entity.UHFTAGInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductEntryActivity extends AppCompatActivity {

    WareHouseWiseDataResponseModel wareHouseWiseDataResponseModel;
    TextView txtProductName, txtProductQnty, txtToolTitle;
    RecyclerView rcvScanAndAddRfidData;
    Button btnScanAndAdd, btnBack, btnCheckAndSave;
    String token;
    CommonService commonService;
    LoadingDialog progressBar;

    public RFIDWithUHFUART mReader;

    private boolean loopFlag = false;
    public ArrayList<HashMap<String, String>> tagList = new ArrayList<HashMap<String, String>>();

    public UHFTAGInfo uhftagInfo = new UHFTAGInfo();
    HashMap<Integer, Integer> soundMap = new HashMap<Integer, Integer>();
    private AudioManager am;
    private SoundPool soundPool;
    private float volumnRatio;
    float productQuantity = 0;
    TextView tv_count, tv_total;
    private HashMap<String, String> map;
    public static final String TAG_EPC = "tagEPC";
    public static final String TAG_EPC_TID = "tagEpcTID";
    public static final String TAG_COUNT = "tagCount";
    public static final String TAG_RSSI = "tagRssi";
    private final List<String> tempDatas = new ArrayList<>();
    private int total;
    public UhfInfo uhfInfo=new UhfInfo();

    Toolbar toolbar;
    ImageView imgBack;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            UHFTAGInfo info = (UHFTAGInfo) msg.obj;
            addDataToList(info.getEPC(),mergeTidEpc(info.getTid(), info.getEPC(),info.getUser()), info.getRssi());

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_entry);
        txtProductName = findViewById(R.id.txt_product_name_rfid_entry);
        txtProductQnty = findViewById(R.id.txt_product_quantity);
        rcvScanAndAddRfidData = findViewById(R.id.rv_warehouse_product_entry);
        btnScanAndAdd = findViewById(R.id.btn_scan_add_warehouse);
        btnBack = findViewById(R.id.btn_back);
        btnCheckAndSave = findViewById(R.id.btn_check_save);
        tv_count = findViewById(R.id.tv_count);
        tv_total = findViewById(R.id.tv_total);

        txtToolTitle = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.custom_toolbar);
        imgBack = findViewById(R.id.img_back);
        txtToolTitle.setText("RFID tag Entry");
        setSupportActionBar(toolbar);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        commonService = new CommonService(ProductEntryActivity.this);
        token = "Bearer " + commonService.getSharedPreferencesValues("loginToken");

        initSound();
        initUHF();
        progressBar = new LoadingDialog(ProductEntryActivity.this, false, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                progressBar.cancel();
            }
        });

        wareHouseWiseDataResponseModel = (WareHouseWiseDataResponseModel) getIntent().getExtras().getSerializable("item");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            wareHouseWiseDataResponseModel = getIntent().getSerializableExtra("item", WareHouseWiseDataResponseModel.class);
            txtProductName.setText(wareHouseWiseDataResponseModel.getProductName());
            txtProductQnty.setText(wareHouseWiseDataResponseModel.getQuantity());

//            productQuantity = Float.parseFloat(wareHouseWiseDataResponseModel.getQuantity());
            getWarehouseErrorData();
//        }

        rcvScanAndAddRfidData.setHasFixedSize(true);
        rcvScanAndAddRfidData.setLayoutManager(new LinearLayoutManager(this));

        btnScanAndAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mReader.startInventoryTag()) {
                    btnScanAndAdd.setText("Stop");
                    loopFlag = true;
//                        setViewEnabled(false);
//                        time = System.currentTimeMillis();
                    new TagThread().start();
                } else {
                    stopInventory();
//					mContext.playSound(2);
                }
            }
        });
    }

    private void getWarehouseErrorData() {
        if (!progressBar.isShowing()) {
            progressBar.show();
        }

        Log.e("token", token);
        ErrorRfidStatusWarehouseBody erwhb = new ErrorRfidStatusWarehouseBody
                (wareHouseWiseDataResponseModel.getMrrDid(),wareHouseWiseDataResponseModel.getProductId());
        RetrofitClient.getClient(ProductEntryActivity.this).getApiService().getWareHouseRfidStatusErrorDataList(token,erwhb)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ErrorRfidStatusResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        Log.e("tag", "start");

                    }

                    @Override
                    public void onNext(ErrorRfidStatusResponse errorResponse) {

                        Log.e("log", "on next");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!progressBar.isShowing()) {
                                    progressBar.hide();
                                    productQuantity = errorResponse.items.size();
                                    CustomWarehouseErrorDataAdapter errorDataAdapter = new CustomWarehouseErrorDataAdapter(errorResponse.items, ProductEntryActivity.this);
                                    rcvScanAndAddRfidData.setAdapter(errorDataAdapter);
                                }
                            }
                        });
                        if (progressBar.isShowing()) {
                            progressBar.hide();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", e.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!progressBar.isShowing()) {
                                    progressBar.hide();
                                }
                            }
                        });
                    }

                    @Override
                    public void onComplete() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!progressBar.isShowing()) {
                                    progressBar.hide();
                                }
                            }
                        });

                    }
                });
    }

    public int checkIsExist(String epc) {
        if (StringUtils.isEmpty(epc)) {
            return -1;
        }
        return binarySearch(tempDatas, epc);
    }
    static int binarySearch(List<String> array, String src) {
        int left = 0;
        int right = array.size() - 1;
        // 这里必须是 <=
        while (left <= right) {
            if (compareString(array.get(left), src)) {
                return left;
            } else if (left != right) {
                if (compareString(array.get(right), src))
                    return right;
            }
            left++;
            right--;
        }
        return -1;
    }

    private String mergeTidEpc(String tid, String epc,String user) {
        String data="EPC:"+ epc;
        if (!TextUtils.isEmpty(tid) && !tid.equals("0000000000000000") && !tid.equals("000000000000000000000000")) {
            data+= "\nTID:" + tid ;
        }
        if(user!=null && user.length()>0) {
            data+="\nUSER:"+user;
        }
        return  data;
    }


    static boolean compareString(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        } else if (str1.hashCode() != str2.hashCode()) {
            return false;
        } else {
            char[] value1 = str1.toCharArray();
            char[] value2 = str2.toCharArray();
            int size = value1.length;
            for (int k = 0; k < size; k++) {
                if (value1[k] != value2[k]) {
                    return false;
                }
            }
            return true;
        }
    }

    private void stopInventory() {
        if (loopFlag) {
            loopFlag = false;
//            setViewEnabled(true);
            if (mReader.stopInventory()) {
                btnScanAndAdd.setText(R.string.btInventory);
                syncDialog();
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!progressBar.isShowing()) {
                            progressBar.show();
                        }
                        Toast.makeText(ProductEntryActivity.this, "Stop failure!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    }


    class TagThread extends Thread {
        public void run() {
            UHFTAGInfo uhftagInfo;
            Message msg;
            while (loopFlag) {
                uhftagInfo = mReader.readTagFromBuffer();
                if (uhftagInfo != null) {
                    msg = handler.obtainMessage();
                    msg.obj = uhftagInfo;
                    handler.sendMessage(msg);
                    Log.e("tag found!! ", new Gson().toJson(uhftagInfo.getEPC()));
                    playSound(1);
                }
            }
        }
    }
    private void syncDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ProductEntryActivity.this);
        builder.setTitle("Want to sync?");

        builder.setMessage("You scan total "+ tv_count.getText().toString() +" Rfid. Want to sync data from server?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (tagList.size() <= 0){
                    return;
                }
                tagList.get(0).get(TAG_EPC);
                List<SaveWarehouseRfidBody.ProductRFIdRequest> productRFIdRequests = new ArrayList<>();
                for (HashMap<String, String> tagData : tagList) {
                    String value = tagData.get(TAG_EPC); // Replace "TAG_EPC" with the actual key for the RFID value
                    SaveWarehouseRfidBody.ProductRFIdRequest productRFIdRequest = new SaveWarehouseRfidBody.ProductRFIdRequest(value);
                    productRFIdRequests.add(productRFIdRequest);
                }
                sendRFIDtoServer(productRFIdRequests);
               /* getListOfRfidWiseData(rfidList, new RfidDataCallback() {
                    @Override
                    public void onDataReceived(ArrayList<AssetUpdateDataModel>  data) {
                        Log.e("Shuvo","going to add data");
                        auda.addItem(data);
                        recAuditUpdateList.setAdapter(auda);
                    }
                });*/
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ProductEntryActivity.this, "Scan again!", Toast.LENGTH_SHORT).show();
                dialogInterface.cancel();
            }
        });

        AlertDialog ad = builder.create();
        ad.show();
    }

    public void playSound(int id) {
        float audioMaxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC); // 返回当前AudioManager对象的最大音量值
        float audioCurrentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);// 返回当前AudioManager对象的音量值
        volumnRatio = audioCurrentVolume / audioMaxVolume;
        try {
            soundPool.play(soundMap.get(id), volumnRatio, // 左声道音量
                    volumnRatio, // 右声道音量
                    1, // 优先级，0为最低
                    0, // 循环次数，0不循环，-1永远循环
                    1 // 回放速度 ，该值在0.5-2.0之间，1为正常速度
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initUHF() {

//        mActionBar = mContext.getSupportActionBar();
//        mActionBar.setDisplayShowTitleEnabled(true);
//        mActionBar.setDisplayShowHomeEnabled(true);
//        mActionBar.setDisplayHomeAsUpEnabled(true);
//        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        try {
            mReader = RFIDWithUHFUART.getInstance();
        } catch (Exception ex) {

//            toastMessage(ex.getMessage());
            return;
        }

        if (mReader != null) {
            new InitTask().execute();
        }
    }

    public class InitTask extends AsyncTask<String, Integer, Boolean> {
        ProgressDialog mypDialog;

        @Override
        protected Boolean doInBackground(String... params) {
            // TODO Auto-generated method stub

            return mReader.init();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            mypDialog.cancel();
            if (!result) {
                Toast.makeText(ProductEntryActivity.this, "init fail", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            mypDialog = new ProgressDialog(ProductEntryActivity.this);
            mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mypDialog.setMessage("wait for configuring device.");
            mypDialog.setCanceledOnTouchOutside(false);
            mypDialog.show();
        }
    }

    private void initSound() {
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 5);
        soundMap.put(1, soundPool.load(ProductEntryActivity.this, R.raw.barcodebeep, 1));
        soundMap.put(2, soundPool.load(ProductEntryActivity.this, R.raw.serror, 1));
        am = (AudioManager) ProductEntryActivity.this.getSystemService(AUDIO_SERVICE);// 实例化AudioManager对象
    }

    private void addDataToList(String epc,String epcAndTidUser, String rssi) {
        if (StringUtils.isNotEmpty(epc) && tagList.size()< productQuantity) {
            int index = checkIsExist(epc);
            map = new HashMap<String, String>();
            map.put(TAG_EPC, epc);
            map.put(TAG_EPC_TID, epcAndTidUser);
            map.put(TAG_COUNT, String.valueOf(1));
            map.put(TAG_RSSI, rssi);
            if (index == -1) {
                tagList.add(map);
                tempDatas.add(epc);
                tv_count.setText(String.valueOf(tagList.size()));
            } else {
                int tagCount = Integer.parseInt(tagList.get(index).get(TAG_COUNT), 10) + 1;
                map.put(TAG_COUNT, String.valueOf(tagCount));
                map.put(TAG_EPC_TID, epcAndTidUser);
                tagList.set(index, map);
            }
            tv_total.setText(String.valueOf(++total));
//            adapter.notifyDataSetChanged();

            //----------
            uhfInfo.setTempDatas(tempDatas);
            uhfInfo.setTagList(tagList);
            uhfInfo.setCount(total);
//            uhfInfo.setTagNumber(adapter.getCount());
        }
        else{
            Toast.makeText(this, "You already read all RFID for this product.", Toast.LENGTH_SHORT).show();
            stopInventory();
        }
    }

    private void releaseSoundPool() {
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }


    private void sendRFIDtoServer(List<SaveWarehouseRfidBody.ProductRFIdRequest> rfidList)
    {
        if (!progressBar.isShowing()) {
            progressBar.show();
        }

        Log.e("token", token);
        int productQnty = (int) productQuantity;
        SaveWarehouseRfidBody swrb = new SaveWarehouseRfidBody
                (productQnty, wareHouseWiseDataResponseModel.getProductId(), wareHouseWiseDataResponseModel.getMrrDid(),"", rfidList);
        RetrofitClient.getClient(ProductEntryActivity.this).getApiService().saveRFIDToServer(token,swrb)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WarehouseRfidSaveResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        Log.e("tag", "start");

                    }

                    @Override
                    public void onNext(WarehouseRfidSaveResponse warehouseRfidResonse) {

                        Log.e("log", "on next");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!progressBar.isShowing()) {
                                    progressBar.hide();
//                                    productQuantity = errorResponse.items.size();
//                                    CustomWarehouseErrorDataAdapter errorDataAdapter = new CustomWarehouseErrorDataAdapter(errorResponse.items, ProductEntryActivity.this);
//                                    rcvScanAndAddRfidData.setAdapter(errorDataAdapter);
                                    Toast.makeText(ProductEntryActivity.this, "Rfid saved successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ProductEntryActivity.this, AddInventoryActivity.class));
                                }
                            }
                        });
                        if (progressBar.isShowing()) {
                            progressBar.hide();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", e.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!progressBar.isShowing()) {
                                    progressBar.hide();
                                }
                            }
                        });
                    }

                    @Override
                    public void onComplete() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!progressBar.isShowing()) {
                                    progressBar.hide();
                                }
                            }
                        });

                    }
                });
    }

    @Override
    public void onDestroy() {
        releaseSoundPool();
        if (mReader != null) {
            mReader.free();
        }
        super.onDestroy();
//        android.os.Process.killProcess(android.os.Process.myPid());
    }
}