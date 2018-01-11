package com.rongyuan.mingyida.module.me;

import android.os.Bundle;

import com.rongyuan.mingyida.R;
import com.rongyuan.mingyida.base.BaseFragment;
import com.rongyuan.mingyida.utils.ToastUtils;

/**
 * Created by guZhongC on 2018/1/5.
 * describe:
 */

public class MeFragment extends BaseFragment {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_me;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ToastUtils.showSuccess(getContext(),"me");
    }
}
