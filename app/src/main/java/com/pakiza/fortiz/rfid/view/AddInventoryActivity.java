package com.pakiza.fortiz.rfid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.api.RetrofitClient;
import com.pakiza.fortiz.rfid.common.CommonService;
import com.pakiza.fortiz.rfid.common.LoadingDialog;
import com.pakiza.fortiz.rfid.model.WarehouseListResponse;
import com.pakiza.fortiz.rfid.model.WarehouseModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddInventoryActivity extends AppCompatActivity {

    String token;
    CommonService commonService;
    LoadingDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);
        commonService = new CommonService(AddInventoryActivity.this);
        token = "Bearer "+commonService.getSharedPreferencesValues("loginToken");

        progressBar = new LoadingDialog(AddInventoryActivity.this, false, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                progressBar.cancel();
            }
        });

        getWarehouseData();

    }

    private void getWarehouseData(){
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

                    Log.e("tag","start");

                    }

                    @Override
                    public void onNext(WarehouseListResponse warehouseListResponse) {

                        Log.e("log","on next");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!progressBar.isShowing()) {
                                    progressBar.hide();
                                }
                            }
                        });
//                        if (!progressBar.isShowing()) {
//                            progressBar.hide();
//                        }
                        if (warehouseListResponse.items.size() > 0) {
//                            locationData = locationListResponse.items;
//                            assetData = assetRfidListResponse.items;
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    llAssetList.setVisibility(View.VISIBLE);
//                                    llAssetSearch.setVisibility(View.VISIBLE);
//                                    if(progressBar.isShowing()){
//                                        progressBar.hide();
//                                    }
//
//                                    ada = new AssetDataAdapter(assetData, AssetOnBoardingActivity.this);
//                                    assetRecyclerView.setAdapter(ada);
//                                }
//                            });
                            WarehouseModel whm = warehouseListResponse.items.get(0);
                            Log.e("whm", whm.getShortName());
                        } else {

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
}