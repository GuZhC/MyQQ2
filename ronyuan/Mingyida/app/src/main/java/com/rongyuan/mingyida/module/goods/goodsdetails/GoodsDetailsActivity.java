package com.rongyuan.mingyida.module.goods.goodsdetails;

import android.os.Bundle;

import com.rongyuan.mingyida.R;
import com.rongyuan.mingyida.base.BaseActivity;

public class GoodsDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        setBackBtn();
        setTitle("商品详情");
    }
}
