package com.pakiza.fortiz.rfid.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.common.CommonService;

public class ApiSettingsActivity extends AppCompatActivity {

    EditText edtApiUrl;
    Button btnSaveApi;
    ImageButton imgBack;
    TextView txtPageTitle;
    CommonService commonService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_settings);
        commonService = new CommonService(ApiSettingsActivity.this);
        edtApiUrl = findViewById(R.id.edt_api_url);
        btnSaveApi = findViewById(R.id.btn_api_sync);
        imgBack = findViewById(R.id.btnBack);
        txtPageTitle = findViewById(R.id.tvPageTitle);

        btnSaveApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = edtApiUrl.getText().toString();
                if (url.isEmpty()){
                    Toast.makeText(ApiSettingsActivity.this, "You have to give an API url!!", Toast.LENGTH_SHORT).show();

                }else{

                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        txtPageTitle.setText("API Settings");
    }

    private void showCustomAlertDialog(String url) {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(ApiSettingsActivity.this);
        builder.setTitle("Enter Credentials");

        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_for_api, null);

        // Find views in the custom layout
        EditText etUserId = dialogView.findViewById(R.id.etUserId);
        EditText etPassword = dialogView.findViewById(R.id.etPassword);

        // Set the custom layout to the builder
        builder.setView(dialogView);

        // Set positive and negative buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle OK button click
                String userId = etUserId.getText().toString();
                String password = etPassword.getText().toString();

                // Use the entered credentials as needed

                if (userId.equals("admin") && password.equals("123")){
                    commonService.storeToSharedPreferences("url", url);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle Cancel button click
                dialog.dismiss();
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}