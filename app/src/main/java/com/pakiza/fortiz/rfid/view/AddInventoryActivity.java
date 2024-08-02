package com.pakiza.fortiz.rfid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.api.RetrofitClient;
import com.pakiza.fortiz.rfid.common.CommonService;
import com.pakiza.fortiz.rfid.common.ConnectivityUtils;
import com.pakiza.fortiz.rfid.common.LoadingDialog;
import com.pakiza.fortiz.rfid.controller.CustomSpinnerAdapter;
import com.pakiza.fortiz.rfid.controller.CustomWarehouseDataAdapter;
import com.pakiza.fortiz.rfid.model.GetAllReceiveNoWiseProductFormWareHouseBody;
import com.pakiza.fortiz.rfid.model.LoginBody;
import com.pakiza.fortiz.rfid.model.LoginResponse;
import com.pakiza.fortiz.rfid.model.StoreModel;
import com.pakiza.fortiz.rfid.model.WarehouseListResponse;
import com.pakiza.fortiz.rfid.model.WarehouseModel;
import com.pakiza.fortiz.rfid.model.WarehouseWiseDataList;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddInventoryActivity extends AppCompatActivity {

    String token;
    CommonService commonService;
    LoadingDialog progressBar;
    List<WarehouseModel> locationList = null;
    Spinner spWareHouse;
    Button btnCheck;
    String selectedWarehouseId;
    EditText edtReceiveNumber, edtBarcode;
    RecyclerView rcvWarehouseData;
    Toolbar toolbar;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);
        commonService = new CommonService(AddInventoryActivity.this);
        token = "Bearer " + commonService.getSharedPreferencesValues("loginToken");
        spWareHouse = findViewById(R.id.sp_warehouse_inventory);
        edtReceiveNumber = findViewById(R.id.edt_receive_number);
        edtBarcode = findViewById(R.id.edt_barcode_number);
        rcvWarehouseData = findViewById(R.id.rv_warehouse_product);
        btnCheck = findViewById(R.id.btn_check);
        toolbar = findViewById(R.id.custom_toolbar);
        imgBack = findViewById(R.id.img_back);
        setSupportActionBar(toolbar);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getWarehouseData();
        progressBar = new LoadingDialog(AddInventoryActivity.this, false, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                progressBar.cancel();
            }
        });

        rcvWarehouseData.setHasFixedSize(true);
        rcvWarehouseData.setLayoutManager(new LinearLayoutManager(this));

        spWareHouse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                WarehouseModel warehouseModel = (WarehouseModel) adapterView.getItemAtPosition(i);
                selectedWarehouseId = warehouseModel.getId();
//                selectedStoreId = storeModel.getStoreId();
//                selectedStoreName = storeModel.getStoreName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String receiveNumer = edtReceiveNumber.getText().toString();
                String barcode = edtBarcode.getText().toString();
                if (!selectedWarehouseId.isEmpty() && !receiveNumer.isEmpty()) {


                    GetAllReceiveNoWiseProductFormWareHouseBody garnwpfwb = new GetAllReceiveNoWiseProductFormWareHouseBody(selectedWarehouseId, "", receiveNumer, barcode);
                    if (ConnectivityUtils.isNetworkConnected(AddInventoryActivity.this)) {

                        if (!progressBar.isShowing()) {
                            progressBar.show();
                        }

                        RetrofitClient.getClient(AddInventoryActivity.this).getApiService().getAllReceiveNoWiseProductFromWareHouse(token, garnwpfwb)
                                .toObservable()
                                .subscribeOn(Schedulers.io())
                                .subscribe(new Observer<WarehouseWiseDataList>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(WarehouseWiseDataList warehouseWiseDataListResponse) {

                                        if (progressBar.isShowing()) {

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    progressBar.hide();

                                                    CustomWarehouseDataAdapter cwa = new
                                                            CustomWarehouseDataAdapter(warehouseWiseDataListResponse.items,
                                                            AddInventoryActivity.this);

                                                    rcvWarehouseData.setAdapter(cwa);
                                                }
                                            });

                                        }

//                                        if (!loginResponse.getResponseStatus().equals("200")) {
//                                            new CommonService(LoginActivity.this).showToastFormBackgroud(loginResponse.getMessages());
//
//                                        }else{
//                                           Log.e("shuvo", loginResponse.getUserModel().getEmpName());



//                                        Log.e("wareh_data",warehouseWiseDataListResponse.items.get(0).getProductName());

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.e("TAG", e.toString());
                                        if (progressBar.isShowing()) {

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(AddInventoryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    progressBar.hide();
                                                }
                                            });

                                        }

                                    }

                                    @Override
                                    public void onComplete() {
                                        if (progressBar.isShowing()) {

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    progressBar.hide();
                                                }
                                            });

                                        }

                                    }
                                });
                    } else {
                        ConnectivityUtils.showNoInternetDialog(AddInventoryActivity.this);
                    }

                } else {
                    if (receiveNumer.isEmpty()) {
                        Toast.makeText(AddInventoryActivity.this, "Receive Number can't be empty.", Toast.LENGTH_SHORT).show();
                    } else if (selectedWarehouseId.isEmpty()) {
                        Toast.makeText(AddInventoryActivity.this, "Warehouse can't be empty.", Toast.LENGTH_SHORT).show();
                    }

                }
            }


        });

    }

    private void getWarehouseData() {
//        if (!progressBar.isShowing()) {
//            progressBar.show();
//        }

        Log.e("token", token);
        RetrofitClient.getClient(AddInventoryActivity.this).getApiService().getWareHouse(token)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WarehouseListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        Log.e("tag", "start");

                    }

                    @Override
                    public void onNext(WarehouseListResponse warehouseListResponse) {

                        Log.e("log", "on next");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!progressBar.isShowing()) {
                                    progressBar.hide();
                                }

                                if (warehouseListResponse.items.size() > 0) {
                                    locationList = warehouseListResponse.items;
                                    CustomSpinnerAdapter csa = new CustomSpinnerAdapter(AddInventoryActivity.this, locationList);
                                    spWareHouse.setAdapter(csa);
//                            Log.e("whm", whm.getShortName());
                                } else {
                                    Toast.makeText(AddInventoryActivity.this, "No data found!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
//                        if (!progressBar.isShowing()) {
//                            progressBar.hide();
//                        }

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
}