package com.rongyuan.mingyida.net.api;

import com.rongyuan.mingyida.model.BannerModel;
import com.rongyuan.mingyida.model.BaseModel;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by guZhongC on 2018/2/2.
 * describe:
 */

public interface BannerApi {
    @FormUrlEncoded
    @POST("banner")
    Observable<BaseModel<List<BannerModel>>> getBanner(@Field("type") String type);
}
