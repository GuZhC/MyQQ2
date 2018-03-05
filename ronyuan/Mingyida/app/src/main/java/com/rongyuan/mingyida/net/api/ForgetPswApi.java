package com.rongyuan.mingyida.net.api;

import com.rongyuan.mingyida.model.BaseModel;
import com.rongyuan.mingyida.model.LoginModel;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by guZhongC on 2018/2/2.
 * describe:
 */

public interface ForgetPswApi {
    @FormUrlEncoded
    @POST("forget")
    Observable<BaseModel<LoginModel>> getForgetPsw(@FieldMap Map<String, String> params);
}
