package com.rongyuan.mingyida.net;


import com.rongyuan.mingyida.net.api.BannerApi;
import com.rongyuan.mingyida.net.api.ForgetPswApi;
import com.rongyuan.mingyida.net.api.GankApi;
import com.rongyuan.mingyida.net.api.GetCodeApi;
import com.rongyuan.mingyida.net.api.LoginApi;
import com.rongyuan.mingyida.net.api.PostCartInforApi;
import com.rongyuan.mingyida.net.api.RegisetrApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络操作类
 */

public class NetWork {

    public static final String ROOT_URL = "http://116.62.235.64/ap/public/app.php/";

    private static GankApi gankApi;
    private static LoginApi mLoginApi;
    private static GetCodeApi mGetCodeApi;
    private static RegisetrApi mRegisetrApi;
    private static ForgetPswApi mForgetPswApi;
    private static BannerApi mBannerApi;

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static PostCartInforApi mPostCartInforApi;

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

    public static GetCodeApi getCodeApi() {
        if (mGetCodeApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            mGetCodeApi = retrofit.create(GetCodeApi.class);
        }
        return mGetCodeApi;
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
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        if (mRegisetrApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            mRegisetrApi = retrofit.create(RegisetrApi.class);
        }
        return mRegisetrApi;
    }

    public static ForgetPswApi getForgetPswApi() {
        if (mForgetPswApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            mForgetPswApi = retrofit.create(ForgetPswApi.class);
        }
        return mForgetPswApi;
    }

    public static BannerApi getBannerApi() {
        if (mBannerApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            mBannerApi = retrofit.create(BannerApi.class);
        }
        return mBannerApi;
    }

    public static PostCartInforApi postCartInforApi() {
        if (mPostCartInforApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            mPostCartInforApi = retrofit.create(PostCartInforApi.class);
        }
        return mPostCartInforApi;
    }
}
