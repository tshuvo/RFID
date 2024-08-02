package com.pakiza.fortiz.rfid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.api.RetrofitClient;
import com.pakiza.fortiz.rfid.common.CommonService;
import com.pakiza.fortiz.rfid.common.LoadingDialog;
import com.pakiza.fortiz.rfid.controller.CustomSpinnerAdapter;
import com.pakiza.fortiz.rfid.controller.CustomStoreSpinnerAdapter;
import com.pakiza.fortiz.rfid.model.AuditDetailsModel;
import com.pakiza.fortiz.rfid.model.BaseResponse;
import com.pakiza.fortiz.rfid.model.CreateAuditBody;
import com.pakiza.fortiz.rfid.model.StoreListResponse;
import com.pakiza.fortiz.rfid.model.StoreModel;
import com.pakiza.fortiz.rfid.model.WarehouseListResponse;
import com.pakiza.fortiz.rfid.model.WarehouseModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateAudit extends AppCompatActivity {

    Spinner spLocation, spWarehouse;
    String token, selectedLocationId, selectedLocationName, selectedStoreId, selectedStoreName;
    CommonService commonService;
    LoadingDialog progressBar;
    List<WarehouseModel> locationList =  null;
    List<StoreModel> storeList =  null;
    Button btnCreateAudit;
    Toolbar toolbar;
    ImageView imgBack;
    TextView txtToolTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_audit);
        getSupportActionBar().hide();
        spLocation = findViewById(R.id.sp_location);
        spWarehouse = findViewById(R.id.sp_warehouse);
        btnCreateAudit = findViewById(R.id.btn_create_audit);

        txtToolTitle = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.custom_toolbar);
        imgBack = findViewById(R.id.img_back);
        txtToolTitle.setText("Warehouse Inventory");
        setSupportActionBar(toolbar);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        commonService = new CommonService(CreateAudit.this);
        token = "Bearer "+commonService.getSharedPreferencesValues("loginToken");

        progressBar = new LoadingDialog(CreateAudit.this, false, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                progressBar.cancel();
            }
        });

        getWarehouseData();
        getStoreData();

        spLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long l) {
                WarehouseModel selectedWarehouse = (WarehouseModel) parentView.getItemAtPosition(position);
                selectedLocationId = selectedWarehouse.getId();
                selectedLocationName = selectedWarehouse.getWareHouseName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spWarehouse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                StoreModel storeModel = (StoreModel) adapterView.getItemAtPosition(i);
                selectedStoreId = storeModel.getStoreId();
                selectedStoreName = storeModel.getStoreName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnCreateAudit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("btn", "clicked");
                createAudit();
            }
        });
    }

    private void getWarehouseData(){
        if (!progressBar.isShowing()) {
            progressBar.show();
        }

        RetrofitClient.getClient(CreateAudit.this).getApiService().getWareHouse(token)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WarehouseListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        Log.e("tag","start");

                    }

                    @Override
                    public void onNext(WarehouseListResponse warehouseListResponse) {

                        Log.e("log","on next");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (progressBar.isShowing()) {
                                    progressBar.hide();
                                }
                            }
                        });
                        if (warehouseListResponse.items.size() > 0) {
                            locationList = warehouseListResponse.items;
                            CustomSpinnerAdapter csa = new CustomSpinnerAdapter(CreateAudit.this, locationList);
                            spLocation.setAdapter(csa);
//                            int initialLocationPosition = spLocation.getSelectedItemPosition();
//                            spLocation.setOnItemSelectedListener(null);
//                            spLocation.setSelection(initialLocationPosition);
                        } else {
                            commonService.showToastFormBackgroud("No locations found!!");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", e.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (progressBar.isShowing()) {
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
                                if (progressBar.isShowing()) {
                                    progressBar.hide();
                                }
                            }
                        });

                    }
                });
    }

    private void getStoreData(){
        if (!progressBar.isShowing()) {
            progressBar.show();
        }

        RetrofitClient.getClient(CreateAudit.this).getApiService().getStores(token)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<StoreListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        Log.e("tag","start");

                    }

                    @Override
                    public void onNext(StoreListResponse storeListResponse) {

                        Log.e("log","on next");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (progressBar.isShowing()) {
                                    progressBar.hide();
                                }
                            }
                        });
                        if (storeListResponse.items.size() > 0) {
                            storeList = storeListResponse.items;
                            CustomStoreSpinnerAdapter csa = new CustomStoreSpinnerAdapter(CreateAudit.this, storeList);
                            spWarehouse.setAdapter(csa);
//                            int initialStorePosition = spWarehouse.getSelectedItemPosition();
//                            spWarehouse.setOnItemSelectedListener(null);
//                            spWarehouse.setSelection(initialStorePosition);
                        } else {
                            commonService.showToastFormBackgroud("No stores found!!");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", e.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (progressBar.isShowing()) {
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
                                if (progressBar.isShowing()) {
                                    progressBar.hide();
                                }
                            }
                        });

                    }
                });
    }

    private void createAudit(){
        if (!progressBar.isShowing()) {
            progressBar.show();
        }

        ArrayList<AuditDetailsModel> audits = new ArrayList<>();
        CreateAuditBody cab = new CreateAuditBody("3fa85f64-5717-4562-b3fc-2c963f66afa6",selectedStoreId, selectedLocationId,"",
                "","","","Abir","InProcess",audits);

        RetrofitClient.getClient(CreateAudit.this).getApiService().createAudit(token,cab)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        Log.e("tag","start");

                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {

                        Log.e("log","on next");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (progressBar.isShowing()) {
                                    progressBar.hide();
                                }
                            }
                        });
                        Log.e("response", baseResponse.getMessages());
//                        if (baseResponse.getResponseStatus()) {
//                            storeList = storeListResponse.items;
//                            CustomStoreSpinnerAdapter csa = new CustomStoreSpinnerAdapter(CreateAudit.this, storeList);
//                            spWarehouse.setAdapter(csa);
//                        } else {
//                            commonService.showToastFormBackgroud("No stores found!!");
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", e.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (progressBar.isShowing()) {
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
                                if (progressBar.isShowing()) {
                                    progressBar.hide();
                                }
                            }
                        });

                    }
                });
    }
}