package com.rongyuan.mingyida.module.nearby;

import com.rongyuan.mingyida.R;
import com.rongyuan.mingyida.base.BaseFragment;
import com.rongyuan.mingyida.utils.ToastUtils;

/**
 * Created by guZhongC on 2018/1/5.
 * describe:
 */

public class NearbyFragment extends BaseFragment {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_nearby;
    }

    @Override
    protected void init() {
        ToastUtils.showSuccess(getContext(),"nearby");
    }
}
