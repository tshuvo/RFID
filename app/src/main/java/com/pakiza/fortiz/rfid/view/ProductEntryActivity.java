package com.pakiza.fortiz.rfid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.api.RetrofitClient;
import com.pakiza.fortiz.rfid.common.CommonService;
import com.pakiza.fortiz.rfid.common.LoadingDialog;
import com.pakiza.fortiz.rfid.controller.CustomSpinnerAdapter;
import com.pakiza.fortiz.rfid.controller.CustomWarehouseErrorDataAdapter;
import com.pakiza.fortiz.rfid.model.ErrorRfidStatusResponse;
import com.pakiza.fortiz.rfid.model.ErrorRfidStatusWarehouseBody;
import com.pakiza.fortiz.rfid.model.WareHouseWiseDataResponseModel;
import com.pakiza.fortiz.rfid.model.WarehouseListResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductEntryActivity extends AppCompatActivity {

    WareHouseWiseDataResponseModel wareHouseWiseDataResponseModel;
    TextView txtProductName, txtProductQnty;
    RecyclerView rcvScanAndAddRfidData;
    Button btnScanAndAdd;
    String token;
    CommonService commonService;
    LoadingDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_entry);
        txtProductName = findViewById(R.id.txt_product_name_rfid_entry);
        txtProductQnty = findViewById(R.id.txt_product_quantity);
        rcvScanAndAddRfidData = findViewById(R.id.rv_warehouse_product_entry);
        btnScanAndAdd = findViewById(R.id.btn_scan_add_warehouse);
        commonService = new CommonService(ProductEntryActivity.this);
        token = "Bearer " + commonService.getSharedPreferencesValues("loginToken");

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
            getWarehouseErrorData();
//        }

        rcvScanAndAddRfidData.setHasFixedSize(true);
        rcvScanAndAddRfidData.setLayoutManager(new LinearLayoutManager(this));

    }

    private void getWarehouseErrorData() {
//        if (!progressBar.isShowing()) {
//            progressBar.show();
//        }

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

                                    CustomWarehouseErrorDataAdapter errorDataAdapter = new CustomWarehouseErrorDataAdapter(errorResponse.items, ProductEntryActivity.this);
                                    rcvScanAndAddRfidData.setAdapter(errorDataAdapter);
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