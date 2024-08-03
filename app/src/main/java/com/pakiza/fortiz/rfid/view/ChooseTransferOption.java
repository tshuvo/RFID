package com.pakiza.fortiz.rfid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.pakiza.fortiz.rfid.R;

public class ChooseTransferOption extends AppCompatActivity {

    LinearLayout llTransfer,llTransferReceive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_transfer_option);

        llTransfer = findViewById(R.id.ll_transfer);
        llTransferReceive = findViewById(R.id.ll_transfer_receive);

        llTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseTransferOption.this, TransferActivity.class));
            }
        });

        llTransferReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseTransferOption.this, TransferReceive.class));
            }
        });
    }
}