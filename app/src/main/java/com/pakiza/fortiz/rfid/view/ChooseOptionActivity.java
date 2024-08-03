package com.pakiza.fortiz.rfid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.common.CommonService;
import com.pakiza.fortiz.rfid.common.LoadingDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChooseOptionActivity extends AppCompatActivity {

    String comeFormActivity = "";
    LinearLayout llNewEntry, llCheckEntry;
    Toolbar toolbar;
    ImageView imgBack;
    TextView txtToolTitle,txtEntryList, txtNewEntry;
    RecyclerView rcWarehouseData;
    LoadingDialog progressBar;
    String token;
    CommonService commonService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_option);

        comeFormActivity = getIntent().getStringExtra("key");
        llNewEntry = findViewById(R.id.ll_new_entry);
        llCheckEntry = findViewById(R.id.ll_check_entry);
        txtNewEntry = findViewById(R.id.txt_new_entry);
        txtToolTitle = findViewById(R.id.toolbar_title);
        txtEntryList = findViewById(R.id.txt_entry_list);
        rcWarehouseData = findViewById(R.id.rv_detail_warehouse);
        toolbar = findViewById(R.id.custom_toolbar);
        imgBack = findViewById(R.id.img_back);
        commonService = new CommonService(ChooseOptionActivity.this);
        token = commonService.getSharedPreferencesValues("loginToken");

        progressBar = new LoadingDialog(ChooseOptionActivity.this, false, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                progressBar.cancel();
            }
        });

        rcWarehouseData.setHasFixedSize(true);
        rcWarehouseData.setLayoutManager(new LinearLayoutManager(ChooseOptionActivity.this));
        rcWarehouseData.addItemDecoration(new DividerItemDecoration(ChooseOptionActivity.this, DividerItemDecoration.VERTICAL));


        if(comeFormActivity.equals("warehouse")){
            txtToolTitle.setText("Warehouse Inventory");
            txtNewEntry.setText("New Entry");
            txtEntryList.setText("Entry List");
        }else if(comeFormActivity.equals("audit")){
            txtToolTitle.setText("Audit");
            txtNewEntry.setText("New Audit");
            txtEntryList.setText("Check Audit");
        }
        setSupportActionBar(toolbar);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        llNewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseOptionActivity.this, AddInventoryActivity.class));
            }
        });

        llCheckEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void getDetailsList() {
        progressBar.show();
//        try {
//            String id = String.valueOf(mActivity.item.id);
//            Log.e("missingId", id);
//            MissingItemBody.SearchTerm st = new MissingItemBody.SearchTerm(id);
//            MissingItemBody mib = new MissingItemBody(1500, 1, st);
//            Log.e("missing item: ", new Gson().toJson(mib));
//            RetrofitClient.getClient().getApiService().getMissingAsset(token, mib)
//                    .toObservable()
//                    .subscribeOn(Schedulers.io())
//                    .subscribe(new Observer<MissingItemResponse>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                            mActivity.runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//
//                                }
//                            });
//
//                        }
//
//                        @Override
//                        public void onNext(MissingItemResponse missingItemResponse) {
//
//                            if (missingItemResponse.items.size() > 0) {
//
//                                mActivity.runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        missingItemList.clear();
//                                        missingItemList.addAll(missingItemResponse.items);
////                                        missingItemList = missingItemResponse.items;
//                                        aada = new AuditAssetDataAdapter(missingItemList, mContext,"miss");
//                                        recAuditMissingList.setAdapter(aada);
//                                        progressBar.hide();
//                                    }
//                                });
//
//
//                            } else {
//
//                                mActivity.runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        progressBar.hide();
//                                        Toast.makeText(mContext, "No onboarded asset Found!!", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//
//                            }
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Log.e("TAG", e.toString());
//                            mActivity.runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    progressBar.hide();
//                                }
//                            });
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                            mActivity.runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    progressBar.hide();
//                                }
//                            });
//
//                        }
//                    });
//        } catch (Exception e) {
//            Log.e("TAG", e.toString());
//        } finally {
//            progressBar.hide();
//        }
    }
}