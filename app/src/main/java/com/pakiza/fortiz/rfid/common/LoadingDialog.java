package com.pakiza.fortiz.rfid.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.pakiza.fortiz.rfid.R;

public class LoadingDialog extends Dialog {
    private Context mContext;

//    public LoadingDialog(Context context, int theme) {
//        super(context, theme);
//        mContext = context;
//    }

    public LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.MATCH_PARENT);
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflateView = inflater.inflate(R.layout.loading_dialog, findViewById(R.id.my_loading_layout), false);
        setContentView(inflateView);
    }
}

