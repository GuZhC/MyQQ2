package com.rongyuan.mingyida.module.me.address;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongyuan.mingyida.R;
import com.rongyuan.mingyida.model.MyAddressModel;

import java.util.List;

/**
 * Created by guZhongC on 2018/2/27.
 * describe:
 */

public class MyAddressAdapter extends BaseQuickAdapter<MyAddressModel, BaseViewHolder> {

    public MyAddressAdapter(@Nullable List<MyAddressModel> data) {
        super(R.layout.item_address_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyAddressModel item) {
        helper.addOnClickListener(R.id.tv_my_address_editor)
                .addOnClickListener(R.id.tv_my_address_delete);

        helper.setText(R.id.tv_my_address_numbername, item.getName() + "  " + item.getPhone());
        helper.setText(R.id.tv_my_address_details, item.getAddress() + item.getDetale_address());
        helper.setVisible(R.id.cb_my_address_default, (item.isIsdefault()));


    }
}
