package com.pakiza.fortiz.rfid.controller;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.pakiza.fortiz.rfid.R;

public class DigitTextWatcher implements TextWatcher {
    private EditText nextDigit;

    public DigitTextWatcher(EditText nextDigit) {
        this.nextDigit = nextDigit;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {

        String otp = s.toString();
        Log.e("otp", otp);
        if (s.length() == 1) {
            // Move the focus to the next digit
            nextDigit.requestFocus();
        }
    }

//    private void moveFocusToNextEditText(EditText currentEditText) {
//        // Move focus to the next EditText
//        switch (currentEditText.getId()) {
//            case R.id.digit1:
//                digit2.requestFocus();
//                break;
//            case R.id.digit2:
//                digit3.requestFocus();
//                break;
//            case R.id.digit3:
//                digit4.requestFocus();
//                break;
//            // Add more cases if needed
//        }
//    }


}
