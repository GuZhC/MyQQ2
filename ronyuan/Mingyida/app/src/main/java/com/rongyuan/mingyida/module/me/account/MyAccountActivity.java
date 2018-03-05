package com.rongyuan.mingyida.module.me.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.rongyuan.mingyida.R;
import com.rongyuan.mingyida.base.BaseActivity;
import com.rongyuan.mingyida.utils.Common;
import com.rongyuan.mingyida.utils.ToastUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyAccountActivity extends BaseActivity {

    @BindView(R.id.me_account_head_image)
    CircleImageView meAccountHeadImage;
    @BindView(R.id.et_me_account_name)
    EditText etMeAccountName;
    @BindView(R.id.ll_my_change_phone)
    LinearLayout llMyChangePhone;
    @BindView(R.id.ll_my_change_psw)
    LinearLayout llMyChangePsw;
    private boolean isChoseImg = false;
    File fileA = null;
    String NowName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.bind(this);
        setBackBtn();
        setTitle("账户与安全");
        NowName = etMeAccountName.getText().toString();
    }

    @OnClick({R.id.me_account_head_image, R.id.ll_my_change_phone, R.id.ll_my_change_psw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.me_account_head_image:
                selectImage();
                break;
            case R.id.ll_my_change_phone:

                break;
            case R.id.ll_my_change_psw:
                if (Common.getKey() == Common.NoLogin)
                    ToastUtils.showInfo(MyAccountActivity.this,"请先登录");
                else
                startActivity(new Intent(this, ChangePswActivity.class));
                break;
        }
    }

    public void selectImage() {
        PictureSelector.create(MyAccountActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .compress(true)
                .maxSelectNum(1)// 最大图片选择数量 int
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    if (selectList != null && selectList.size() > 0) {
                        isChoseImg = true;
                        Common.ShowImage(MyAccountActivity.this, selectList.get(0).getPath(), meAccountHeadImage);
                        if (selectList.get(0).isCompressed()) {
                            fileA = new File(selectList.get(0).getCompressPath());
                        } else {
                            fileA = new File(selectList.get(0).getPath());
                        }

                    }
                    break;
            }
        }
    }

    public void onBackPressed() {
        if (isChoseImg || !etMeAccountName.equals(NowName)) {
            //todo  post
            finish();
        } else {
            finish();
        }
    }
}
