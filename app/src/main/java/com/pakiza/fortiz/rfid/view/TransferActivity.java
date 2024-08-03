package com.pakiza.fortiz.rfid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.common.CommonService;
import com.pakiza.fortiz.rfid.common.LoadingDialog;
import com.pakiza.fortiz.rfid.model.WarehouseModel;

import java.util.List;

public class TransferActivity extends AppCompatActivity {
    Spinner spTransferTo;
    String selectedTransferWarehouse, token;
    CommonService commonService;
    LoadingDialog progressBar;
    EditText edtRemarks;
    TextView txtTime;
    List<WarehouseModel> warehouseList = null;
    Button btnScanRfid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        commonService = new CommonService(TransferActivity.this);
        token = "Bearer " + commonService.getSharedPreferencesValues("loginToken");
        edtRemarks = findViewById(R.id.edt_remarks);
        txtTime = findViewById(R.id.txt_current_date);
        btnScanRfid = findViewById(R.id.btn_scan_rfid);

        spTransferTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                WarehouseModel warehouseModel = (WarehouseModel) adapterView.getItemAtPosition(i);
                selectedTransferWarehouse = warehouseModel.getId();
//                selectedStoreId = storeModel.getStoreId();
//                selectedStoreName = storeModel.getStoreName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}