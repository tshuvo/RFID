package com.pakiza.fortiz.rfid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.api.RetrofitClient;
import com.pakiza.fortiz.rfid.common.CommonService;
import com.pakiza.fortiz.rfid.common.LoadingDialog;
import com.pakiza.fortiz.rfid.controller.CustomSpinnerAdapter;
import com.pakiza.fortiz.rfid.model.WarehouseListResponse;
import com.pakiza.fortiz.rfid.model.WarehouseModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailList extends AppCompatActivity {

    Spinner spWarehouse;
    String selectedWarehouseId, token;
    CommonService commonService;
    LoadingDialog progressBar;
    EditText edtDetailOne, edtDetailTwo;
    List<WarehouseModel> warehouseList = null;
    Button btnDetailsSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);
        commonService = new CommonService(DetailList.this);
        edtDetailOne = findViewById(R.id.edt_detail_receive_number);
        edtDetailTwo = findViewById(R.id.edt_detail_barcode);
        btnDetailsSearch = findViewById(R.id.btn_details_search);
        spWarehouse = findViewById(R.id.sp_first_details);
        getWarehouseData();
        token = "Bearer " + commonService.getSharedPreferencesValues("loginToken");
        spWarehouse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        btnDetailsSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String edtOneInput, edtTwoInput;
                edtOneInput = edtDetailOne.getText().toString();
                edtTwoInput = edtDetailTwo.getText().toString();
                if (selectedWarehouseId.isEmpty()){
                    Toast.makeText(DetailList.this, "Please, select a warehouse.", Toast.LENGTH_SHORT).show();
                }else if(edtOneInput.isEmpty() && edtTwoInput.isEmpty()){
                    Toast.makeText(DetailList.this, "Please, input Receive Number or Barcode!", Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });
    }


    private void getWarehouseData() {
        if (!progressBar.isShowing()) {
            progressBar.show();
        }

        Log.e("token", token);
        RetrofitClient.getClient(DetailList.this).getApiService().getWareHouse(token)
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
                                    warehouseList = warehouseListResponse.items;
                                    CustomSpinnerAdapter csa = new CustomSpinnerAdapter(DetailList.this, warehouseList);
                                    spWarehouse.setAdapter(csa);
//                            Log.e("whm", whm.getShortName());
                                } else {
                                    Toast.makeText(DetailList.this, "No data found!!", Toast.LENGTH_SHORT).show();
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