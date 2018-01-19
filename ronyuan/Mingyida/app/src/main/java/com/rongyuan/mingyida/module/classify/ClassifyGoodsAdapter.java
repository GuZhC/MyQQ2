package com.rongyuan.mingyida.module.classify;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongyuan.mingyida.R;
import com.rongyuan.mingyida.model.GoodsModel;

import java.util.List;

/**
 * Created by guZhongC on 2018/1/18.
 * describe:
 */

public class ClassifyGoodsAdapter extends BaseQuickAdapter<GoodsModel, BaseViewHolder> {

    public ClassifyGoodsAdapter(@Nullable List<GoodsModel> data) {
        super(R.layout.item_classify_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsModel item) {
        helper.setText(R.id.tv_classify_goods_name, item.getTitle());
        Glide.with(mContext)
                .load(item.getImgUrl())
                .placeholder(R.mipmap.image_default)
                .error(R.drawable.errorview)
                .crossFade(800)
                .into((ImageView) helper.getView(R.id.iv_classify_goods_img));
    }
}
