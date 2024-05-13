package com.pakiza.fortiz.rfid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.pakiza.fortiz.rfid.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llWarehouse, llRetailStore, llAudit, llSettings, llHelp, llLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        llWarehouse = findViewById(R.id.ll_warehouse);
        llRetailStore = findViewById(R.id.ll_retail_store);
        llAudit= findViewById(R.id.ll_audit);
        llSettings = findViewById(R.id.ll_settings);
        llHelp = findViewById(R.id.ll_help);
        llLogout = findViewById(R.id.ll_logout);


        llWarehouse.setOnClickListener(this);
        llRetailStore.setOnClickListener(this);
        llAudit.setOnClickListener(this);
        llSettings.setOnClickListener(this);
        llHelp.setOnClickListener(this);
        llLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_warehouse:
                startActivity(new Intent(HomeActivity.this, ChooseOptionActivity.class)
                        .putExtra("key","warehouse"));
                break;

            case R.id.ll_audit:
                startActivity(new Intent(HomeActivity.this, ChooseOptionActivity.class)
                        .putExtra("key","audit"));
                break;
        }
    }
}