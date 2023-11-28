package com.pakiza.fortiz.rfid.api;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtil {

    private static final String PREF_NAME = "your_preferences_name";
    private static final String KEY_BASE_URL = "base_url";

    public static void saveBaseUrl(Context context, String baseUrl) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_BASE_URL, baseUrl);
        editor.apply();
    }

    public static String getBaseUrl(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_BASE_URL, "default_base_url");
    }
}
