package com.rongyuan.mingyida.net;


import android.util.Log;

import com.rongyuan.mingyida.model.RegisterModel;
import com.rongyuan.mingyida.net.api.GankApi;
import com.rongyuan.mingyida.net.api.LoginApi;
import com.rongyuan.mingyida.net.api.RegisetrApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络操作类
 *
 */

public class NetWork {

    public static final String ROOT_URL = "http://192.168.0.109:8080/pyt/public/index.php/apis/";

    private static GankApi gankApi;
    private static LoginApi mLoginApi;
    private static RegisetrApi mRegisetrApi;

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static GankApi getGankApi() {
        if (gankApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            gankApi = retrofit.create(GankApi.class);
        }
        return gankApi;
    }


    public static LoginApi getLoginApi() {
        if (mLoginApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            mLoginApi = retrofit.create(LoginApi.class);
        }
        return mLoginApi;
    }
    public static RegisetrApi getRegisterApi() {
        if (mRegisetrApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            mRegisetrApi = retrofit.create(RegisetrApi.class);
        }
        Log.e("oooooo","getRegisterApi");
        return mRegisetrApi;
    }
}
