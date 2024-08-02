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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.api.RetrofitClient;
import com.pakiza.fortiz.rfid.common.LoadingDialog;
import com.pakiza.fortiz.rfid.controller.DigitTextWatcher;
import com.pakiza.fortiz.rfid.model.BaseResponse;
import com.pakiza.fortiz.rfid.model.ResetOtpBody;
import com.pakiza.fortiz.rfid.model.ResetOtpResponse;
import com.pakiza.fortiz.rfid.model.SendOtpBody;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText edtDigit1, edtDigit2, edtDigit3, edtDigit4, edtForgetPassEmail, edtNewPass;
    TextView txtLogin,txtNewPass, txtOtp;
    String loginText;
    Button btnReset;
    LinearLayout llEditBox;
    LoadingDialog progressBar;
    Toolbar toolbar;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        getSupportActionBar().hide();


        txtLogin =findViewById(R.id.txt_login);
        btnReset = findViewById(R.id.btn_reset);
        edtDigit1 =findViewById(R.id.digit1);
        edtDigit2 =findViewById(R.id.digit2);
        edtDigit3 =findViewById(R.id.digit3);
        edtDigit4 =findViewById(R.id.digit4);
        edtForgetPassEmail = findViewById(R.id.edt_foget_pass_email);
        edtNewPass = findViewById(R.id.edt_new_pass);
        txtNewPass =findViewById(R.id.txt_new_pass);
        txtOtp = findViewById(R.id.txt_otp_text);
        llEditBox = findViewById(R.id.ll_otp_box);

        progressBar = new LoadingDialog(ResetPasswordActivity.this, false, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                progressBar.cancel();
            }
        });

        loginText = getString(R.string.login);
        btnReset.setText("send otp");
        SpannableString spannableString = new SpannableString(Html.fromHtml(loginText));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
            }
        };

        int startIndex = loginText.indexOf("LOGIN");
        int endIndex = startIndex + "LOGIN".length();
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtLogin.setText(spannableString);
        txtLogin.setMovementMethod(LinkMovementMethod.getInstance());
        edtDigit1.addTextChangedListener(new DigitTextWatcher(edtDigit2));
        edtDigit2.addTextChangedListener(new DigitTextWatcher(edtDigit3));
        edtDigit3.addTextChangedListener(new DigitTextWatcher(edtDigit4));

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnReset.getText().toString().equals("send otp")){
                    String email = edtForgetPassEmail.getText().toString();
                    if (email.isEmpty()){
                        Toast.makeText(ResetPasswordActivity.this,
                                "Please, write an email for sending OTP!!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    sendOtpToEmail(email);
                }else{
                    resetOtp();
                }
                Log.e("otpss", getEnteredText());
            }
        });


    }

    private void sendOtpToEmail(String email){

        if (!progressBar.isShowing()) {
            progressBar.show();
        }

        SendOtpBody sob = new SendOtpBody(email);
        RetrofitClient.getClient(ResetPasswordActivity.this).getApiService()
                .sendOtpTOEmail(sob)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse value) {

                        if (progressBar.isShowing()) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.hide();
                                    Toast.makeText(ResetPasswordActivity.this, value.getMessages(), Toast.LENGTH_SHORT).show();
                                    btnReset.setText("Reset");
                                    llEditBox.setVisibility(View.VISIBLE);
                                    txtNewPass.setVisibility(View.VISIBLE);
                                    txtOtp.setVisibility(View.VISIBLE);
                                    edtNewPass.setVisibility(View.VISIBLE);
                                    edtForgetPassEmail.setVisibility(View.GONE);
//
                                }
                            });

                        }

                        Log.e("respone:", value.getResponseStatus());

//                        if (value.getResponseStatus().equals("200")){
//                            Toast.makeText(ResetPasswordActivity.this, value.getMessages(), Toast.LENGTH_SHORT).show();
//                            btnReset.setText("Reset");
//                            llEditBox.setVisibility(View.VISIBLE);
//                            txtNewPass.setVisibility(View.VISIBLE);
//                            edtNewPass.setVisibility(View.VISIBLE);
//                            edtForgetPassEmail.setVisibility(View.GONE);
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (progressBar.isShowing()) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
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
    }

    private void resetOtp(){

        String newPass = edtNewPass.getText().toString();
        String otp = getEnteredText();
        if (otp.length()<4){
            Toast.makeText(this, "Please, enter correct OTP!!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (newPass.isEmpty()){
            Toast.makeText(this, "Please, enter your new password!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!progressBar.isShowing()) {
            progressBar.show();
        }

        ResetOtpBody rob = new ResetOtpBody(newPass, otp);
        RetrofitClient.getClient(ResetPasswordActivity.this).getApiService()
                .resendOtp(rob)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResetOtpResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResetOtpResponse value) {

                        if (progressBar.isShowing()) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.hide();
                                    Toast.makeText(ResetPasswordActivity.this, value.getMessages(), Toast.LENGTH_SHORT).show();
                                    if (value.getResponseStatus().equals("200")){
                                        startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                                    }

                                }
                            });

                        }

                        Log.e("respone:", value.getResponseStatus());

//                        if (value.getResponseStatus().equals("200")){
//                            Toast.makeText(ResetPasswordActivity.this, value.getMessages(), Toast.LENGTH_SHORT).show();
//                            btnReset.setText("Reset");
//                            llEditBox.setVisibility(View.VISIBLE);
//                            txtNewPass.setVisibility(View.VISIBLE);
//                            edtNewPass.setVisibility(View.VISIBLE);
//                            edtForgetPassEmail.setVisibility(View.GONE);
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (progressBar.isShowing()) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
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
    }
    private String getEnteredText() {
        // Concatenate the text from all EditTexts
        return edtDigit1.getText().toString() +
                edtDigit2.getText().toString() +
                edtDigit3.getText().toString() +
                edtDigit4.getText().toString();
    }
}