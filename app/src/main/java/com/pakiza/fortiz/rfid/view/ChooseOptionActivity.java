package com.pakiza.fortiz.rfid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pakiza.fortiz.rfid.R;

public class ChooseOptionActivity extends AppCompatActivity {

    String comeFormActivity = "";
    LinearLayout llNewEntry, llCheckEntry;
    Toolbar toolbar;
    ImageView imgBack;
    TextView txtToolTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_option);

        comeFormActivity = getIntent().getStringExtra("key");
        llNewEntry = findViewById(R.id.ll_new_entry);
        llCheckEntry = findViewById(R.id.ll_check_entry);
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
}