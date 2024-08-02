package com.pakiza.fortiz.rfid.common;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils {

    private static final String PREFS_NAME = "MyAppPrefs";

    public static void storeStringInPrefs(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public static String getStringFromPrefs(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }
}
