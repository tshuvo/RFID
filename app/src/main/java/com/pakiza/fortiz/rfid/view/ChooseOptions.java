package com.pakiza.fortiz.rfid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.api.RetrofitClient;
import com.pakiza.fortiz.rfid.common.CommonService;
import com.pakiza.fortiz.rfid.common.ConnectivityUtils;
import com.pakiza.fortiz.rfid.common.LoadingDialog;
import com.pakiza.fortiz.rfid.common.Utils;
import com.pakiza.fortiz.rfid.controller.AllCompanyAdapter;
import com.pakiza.fortiz.rfid.controller.AllWarehouseAdapter;
import com.pakiza.fortiz.rfid.controller.CustomSpinnerAdapter;
import com.pakiza.fortiz.rfid.controller.CustomWarehouseDataAdapter;
import com.pakiza.fortiz.rfid.model.AllCompanyResponse;
import com.pakiza.fortiz.rfid.model.AllWarehouseResponse;
import com.pakiza.fortiz.rfid.model.CompanyDataModel;
import com.pakiza.fortiz.rfid.model.GetAllReceiveNoWiseProductFormWareHouseBody;
import com.pakiza.fortiz.rfid.model.GetWarehouseBody;
import com.pakiza.fortiz.rfid.model.WarehouseDataModel;
import com.pakiza.fortiz.rfid.model.WarehouseModel;
import com.pakiza.fortiz.rfid.model.WarehouseWiseDataList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChooseOptions extends AppCompatActivity {
    String token;
    CommonService commonService;
    LoadingDialog progressBar;
    String regionText, selectedCompanyId, selectedWarehouseId;
    Spinner spCompany, spWarehouse;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_options);

        spCompany = findViewById(R.id.sp_company);
        spWarehouse = findViewById(R.id.sp_area);
        btnSubmit = findViewById(R.id.btn_submit);
        commonService = new CommonService(ChooseOptions.this);
        token = "Bearer " + commonService.getSharedPreferencesValues("loginToken");
        regionText = Utils.getStringFromPrefs(ChooseOptions.this, "a_region");
        progressBar = new LoadingDialog(ChooseOptions.this, false, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                progressBar.cancel();
            }
        });
        GetWarehouseBody getWarehouseBody = new GetWarehouseBody(regionText);
        spWarehouse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                WarehouseDataModel warehouseModel = (WarehouseDataModel) adapterView.getItemAtPosition(i);
                selectedWarehouseId = warehouseModel.getId();
//                selectedStoreId = storeModel.getStoreId();
//                selectedStoreName = storeModel.getStoreName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CompanyDataModel company = (CompanyDataModel) adapterView.getItemAtPosition(i);
                selectedCompanyId = company.getCompanyID();
//                selectedStoreId = storeModel.getStoreId();
//                selectedStoreName = storeModel.getStoreName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedCompanyId.isEmpty() || selectedWarehouseId.isEmpty()){
                    Toast.makeText(ChooseOptions.this, "Please choose area to proceed!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Utils.storeStringInPrefs(ChooseOptions.this, "company", selectedCompanyId);
                Utils.storeStringInPrefs(ChooseOptions.this, "area", selectedWarehouseId);
                startActivity(new Intent(ChooseOptions.this, HomeActivity.class));
            }
        });

        if (ConnectivityUtils.isNetworkConnected(ChooseOptions.this)) {

            if (!progressBar.isShowing()) {
                progressBar.show();
            }

            RetrofitClient.getClient(ChooseOptions.this).getApiService().getAllCompany(token)
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<AllCompanyResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(AllCompanyResponse allCompanyResponse) {

                            if (progressBar.isShowing()) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.hide();
                                        Log.e("res", allCompanyResponse.getData().size()+"");
                                        if (allCompanyResponse.getData().size() > 0) {

                                            AllCompanyAdapter aca = new AllCompanyAdapter(ChooseOptions.this, allCompanyResponse.getData());
                                            spCompany.setAdapter(aca);

                                        } else {
                                            Toast.makeText(ChooseOptions.this, "No data found!!", Toast.LENGTH_SHORT).show();
                                        }
//
                                    }
                                });

                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("TAG", e.toString());
                            if (progressBar.isShowing()) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ChooseOptions.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

                RetrofitClient.getClient(ChooseOptions.this).getApiService().getAllWarehouse(token, getWarehouseBody)
                        .toObservable()
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<AllWarehouseResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(AllWarehouseResponse allWarehouseResponse) {

                                if (progressBar.isShowing()) {

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressBar.hide();
                                            Log.e("res_ware", allWarehouseResponse.getData().size()+"");
                                            if (allWarehouseResponse.getData().size() > 0) {

                                                AllWarehouseAdapter aca = new AllWarehouseAdapter(ChooseOptions.this, allWarehouseResponse.getData());
                                                spWarehouse.setAdapter(aca);
//                            Log.e("whm", whm.getShortName());
                                            } else {
                                                Toast.makeText(ChooseOptions.this, "No data found!!", Toast.LENGTH_SHORT).show();
                                            }
//                                            Log.e("res_ware", allWarehouseResponse.getData().get(0).getOutletName());
                                        }
                                    });

                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("TAG", e.toString());
                                if (progressBar.isShowing()) {

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(ChooseOptions.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                ConnectivityUtils.showNoInternetDialog(ChooseOptions.this);

        }
    }
}