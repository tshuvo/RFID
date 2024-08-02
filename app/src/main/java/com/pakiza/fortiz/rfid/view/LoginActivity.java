package com.pakiza.fortiz.rfid.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.api.RetrofitClient;
import com.pakiza.fortiz.rfid.common.CommonService;
import com.pakiza.fortiz.rfid.common.ConnectivityUtils;
import com.pakiza.fortiz.rfid.common.LoadingDialog;
import com.pakiza.fortiz.rfid.common.Utils;
import com.pakiza.fortiz.rfid.model.LoginBody;
import com.pakiza.fortiz.rfid.model.LoginResponse;
import com.pakiza.fortiz.rfid.model.UserModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtEmail, edtPass;
    String email , pass;
    String resetText ;
    TextView txtResetText;
    LinearLayout llSettings;
//    UserProfile userProfile;
    CommonService commonService;
    LoadingDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        commonService = new CommonService(LoginActivity.this);
        btnLogin = findViewById(R.id.btn_login);
        edtEmail = findViewById(R.id.edt_email);
        edtPass = findViewById(R.id.edt_pass);
        txtResetText = findViewById(R.id.txt_reset_text);
        llSettings = findViewById(R.id.ll_api_settings);

        progressBar = new LoadingDialog(LoginActivity.this, false, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                progressBar.cancel();
            }
        });


        llSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ApiSettingsActivity.class));
            }
        });

        resetText = getString(R.string.reset_pass);
        SpannableString spannableString = new SpannableString(Html.fromHtml(resetText));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        };

        int startIndex = resetText.indexOf("RESET");
        int endIndex = startIndex + "RESET".length();
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtResetText.setText(spannableString);
        txtResetText.setMovementMethod(LinkMovementMethod.getInstance());


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                email = edtEmail.getText().toString();
                pass = edtPass.getText().toString();

                if (email.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email can't be empty.", Toast.LENGTH_SHORT).show();
                } else if (pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Password can't be empty.", Toast.LENGTH_SHORT).show();
                } else {


                    LoginBody lb = new LoginBody(email, pass);
                    if(ConnectivityUtils.isNetworkConnected(LoginActivity.this)){

                        if (!progressBar.isShowing()) {
                            progressBar.show();
                        }

                        RetrofitClient.getClient(LoginActivity.this).getApiService().getLoginApi(lb)
                                .toObservable()
                                .subscribeOn(Schedulers.io())
                                .subscribe(new Observer<LoginResponse>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(LoginResponse loginResponse) {

                                        if (progressBar.isShowing()) {

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    progressBar.hide();
                                                }
                                            });

                                        }

//                                        if (!loginResponse.getResponseStatus().equals("200")) {
//                                            new CommonService(LoginActivity.this).showToastFormBackgroud(loginResponse.getMessages());
//
//                                        }else{
//                                           Log.e("shuvo", loginResponse.getUserModel().getEmpName());

                                            Log.e("login","login complete");
                                            Gson gson = new Gson();
                                            UserModel userModel = loginResponse.getUserModel();
                                            String userProfileData = gson.toJson(loginResponse.getUserModel());

                                            commonService.storeToSharedPreferences("userProfile", userProfileData);
                                            commonService.storeToSharedPreferences("loginToken", loginResponse.getToken());

                                            if(userModel.getActiveRegions().isEmpty()){
                                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                            }else{
                                                Utils.storeStringInPrefs(LoginActivity.this,"a_region", userModel.getActiveRegions());
                                                startActivity(new Intent(LoginActivity.this, ChooseOptions.class));
                                            }
//                                        }


                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.e("TAG", e.toString());
                                        if (progressBar.isShowing()) {

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    progressBar.hide();
                                                }
                                            });

                                        }

                                    }

                                    @Override
                                    public void onComplete() {
                                        if (progressBar.isShowing()) {

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    progressBar.hide();
                                                }
                                            });

                                        }

                                    }
                                });
                    }else{
                        ConnectivityUtils.showNoInternetDialog(LoginActivity.this);
                    }

                }


            }
        });
    }
}