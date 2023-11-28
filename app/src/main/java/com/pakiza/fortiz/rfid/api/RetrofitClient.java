package com.pakiza.fortiz.rfid.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private static String baseUrl;
    private RetrofitClient() {

    }

    public static RetrofitClient getClient(Context context) {
        if (instance == null) {
            instance = new RetrofitClient();
            baseUrl = getBaseUrlFromPreferences(context);
        }
        return instance;
    }

    public ApiService getApiService() {
        return retrofit().create(ApiService.class);
    }

    //-------------------- FOR ERROR PARSE ONLY --------------------
    public static Retrofit getRetrofit(Context context) {
        baseUrl = getBaseUrlFromPreferences(context);
        return retrofit();
    }

    private static Retrofit retrofit() {
        return new Retrofit
                .Builder()
                .baseUrl(ApiUrl.BASE_URL) /* todo need to update this one. User baseUrl(local variable)*/
                .client(okHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient okHttpClient() {
        // Context context = Apps.getAppsContext();
        return new OkHttpClient
                .Builder()
                .callTimeout(40, TimeUnit.SECONDS)
                .connectTimeout(40, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                // .cache(cache(context))
                // .addInterceptor(httpLoggingInterceptor())   //--- used if network off OR on
                // .addNetworkInterceptor(networkInterceptor())   //--- only used when network is on
                // .addInterceptor(offlineInterceptor(context))
                .build();
    }


    private static String getBaseUrlFromPreferences(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("url", "");
    }
}
