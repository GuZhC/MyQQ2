package com.rongyuan.mingyida.module.home;

import com.rongyuan.mingyida.base.BasePresenter;
import com.rongyuan.mingyida.base.BaseView;

import java.util.List;

/**
 * Created by guZhongC on 2018/1/5.
 * describe:
 */

public interface HomeContract {
    interface IHomeView extends BaseView {
        void showBannerFail(String failMessage);

        void setBanner(List<String> imgUrls);
    }

    interface IHomePresenter extends BasePresenter{
        void getBannerData();
    }
}
