package com.rongyuan.mingyida.module.goods.goodsdetails;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongyuan.mingyida.R;
import com.rongyuan.mingyida.model.CommentModel;
import com.rongyuan.mingyida.utils.Common;

import java.util.List;

/**
 * Created by guZhongC on 2018/2/26.
 * describe:
 */

public class CommentAdapter extends BaseQuickAdapter<CommentModel, BaseViewHolder> {
    public CommentAdapter(@Nullable List<CommentModel> data) {
        super(R.layout.item_comment_recycler, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentModel item) {
        helper.setText(R.id.tv_evaluation_name, item.getUsername());
        helper.setText(R.id.tv_evaluation_time, item.getTime());
        helper.setRating(R.id.rb_evaluation_rating, item.getStarLevel());
        helper.setText(R.id.tv_evaluation_content, item.getCommentContent());
        Common.ShowImage(mContext, item.getHeadimage(), (ImageView) helper.getView(R.id.iv_evaluation_head));
        if (item.getCommentimages().size() > 0) {
            helper.setVisible(R.id.ll_evaluation_images, true);
            Common.ShowImage(mContext, item.getCommentimages().get(0), (ImageView) helper.getView(R.id.iv_evaluation_imga));
            if (item.getCommentimages().size() > 1) {
                Common.ShowImage(mContext,  item.getCommentimages().get(1), (ImageView) helper.getView(R.id.iv_evaluation_imgb));
                if (item.getCommentimages().size() > 2) {
                    Common.ShowImage(mContext,  item.getCommentimages().get(2), (ImageView) helper.getView(R.id.iv_evaluation_imgc));
                }
            }
        } else {
            helper.setGone(R.id.ll_evaluation_images, false);
        }
    }
}
