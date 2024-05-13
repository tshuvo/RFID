package com.pakiza.fortiz.rfid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.pakiza.fortiz.rfid.R;

public class ChooseOptionActivity extends AppCompatActivity {

    String comeFormActivity = "";
    LinearLayout llNewEntry, llCheckEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_option);

        comeFormActivity = getIntent().getStringExtra("key");
        llNewEntry = findViewById(R.id.ll_new_entry);
        llCheckEntry = findViewById(R.id.ll_check_entry);

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