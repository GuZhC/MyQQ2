package com.rongyuan.mingyida.module.home;

import android.view.View;
import android.widget.TextView;

import com.rongyuan.mingyida.R;
import com.rongyuan.mingyida.base.BaseFragment;
import com.rongyuan.mingyida.utils.GlideImageLoader;
import com.rongyuan.mingyida.utils.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by guZhongC on 2018/1/5.
 * describe:
 */

public class HomeFragment extends BaseFragment implements HomeContract.IHomeView, OnBannerListener {

    @BindView(R.id.tv_home_info)
    TextView tvHomeInfo;
    @BindView(R.id.home_banner)
    Banner mBanner;

    @BindView(R.id.ed_home_search)
    TextView edHomeSearch;

    //
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        initBanner(); //todo  测试用

    }

    private void initBanner() {
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.setOnBannerListener(this);

        List<String> imgUrls = new ArrayList<>();
        imgUrls.add("http://www.songlankeji.com/static/index/image/zzgg%20(2).png");
        imgUrls.add("http://www.songlankeji.com/static/index/image/zzgg%20(2).png");
        imgUrls.add("http://www.songlankeji.com/static/index/image/zzgg%20(2).png");
        mBanner.setImages(imgUrls).setImageLoader(new GlideImageLoader()).start();
    }

    @Override
    public void showBannerFail(String failMessage) {
        ToastUtils.showError(getContext(), failMessage);
    }

    @Override
    public void setBanner(List<String> imgUrls) {
        mBanner.setImages(imgUrls).setImageLoader(new GlideImageLoader()).start();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @OnClick({R.id.ed_home_search, R.id.tv_home_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_home_search:
                break;
            case R.id.tv_home_info:
                break;
        }
    }

    @Override
    public void OnBannerClick(int position) {

    }
}
