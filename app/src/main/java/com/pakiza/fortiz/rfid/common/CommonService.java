package com.pakiza.fortiz.rfid.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.widget.Toast;

import com.pakiza.fortiz.rfid.R;

public class CommonService {

    Context mcontext;

    public CommonService(Context context) {
        this.mcontext = context;
    }

    public void storeToSharedPreferences(String name, String value){
        SharedPreferences preferences = mcontext.getSharedPreferences(mcontext.getPackageName(),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, value).apply();
    }

    public String getSharedPreferencesValues(String name){
        SharedPreferences preferences = mcontext.getSharedPreferences(mcontext.getPackageName(),Context.MODE_PRIVATE);
        return preferences.getString(name,"");
    }

    public void deleteSharedPreferencesValues(String name){
        SharedPreferences preferences = mcontext.getSharedPreferences(mcontext.getPackageName(),Context.MODE_PRIVATE);
        preferences.edit().remove(name).apply();
    }

    public void showToastFormBackgroud(String massage){
        Handler handler = new Handler(Looper.getMainLooper());

        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(mcontext, massage, Toast.LENGTH_LONG).show();
            }
        });
    }

    public AlertDialog.Builder setAlertDialog(String massage){
        AlertDialog.Builder alert = new AlertDialog.Builder(mcontext, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        alert.setMessage(massage);

        return alert;
    }

    public void showDialog()
    {
        // Create the dialog.
        final Dialog emailDialog =
                new Dialog(mcontext, android.R.style.Theme_Dialog);
        emailDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        emailDialog.setCancelable(true);
        emailDialog.setContentView(R.layout.loading_dialog);

        // Get dialog widgets references.
//        final EditText editFriendsEmail = (EditText)emailDialog.findViewById(R.id.editEmailAddFriendEmail);
//        Button btnAccept = (Button)emailDialog.findViewById(R.id.btnAddFriendEmail);

        // Set on click lister for accept button
//        btnAccept.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                // Get selected values
//                String friendEmail =
//                        editFriendsEmail.getText().toString();
//
//                // Close dialog.
//                emailDialog.dismiss();
//
//                // TODO: Call api to send email to web service using friendsEmail var.
//
//            }
//        });

        //now that the dialog is set up, it's time to show it
        emailDialog.show();
    }


}
