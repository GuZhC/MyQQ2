package com.rongyuan.mingyida.module.me.cartinfo;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.rongyuan.mingyida.R;
import com.rongyuan.mingyida.base.BaseActivity;
import com.rongyuan.mingyida.model.BaseModel;
import com.rongyuan.mingyida.net.NetWork;
import com.rongyuan.mingyida.ui.MyLoader;
import com.rongyuan.mingyida.utils.Common;
import com.rongyuan.mingyida.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddMyCart extends BaseActivity {

    @BindView(R.id.my_cart_number_add_chejia)
    EditText myCartNumberAddChejia;
    @BindView(R.id.my_cart_number_add_chepai)
    EditText myCartNumberAddChepai;
    @BindView(R.id.my_cart_number_add_fadonji)
    EditText myCartNumberAddFadonji;
    @BindView(R.id.my_cart_number_add_daihao)
    EditText myCartNumberAddDaihao;
    @BindView(R.id.my_cart_number_add_function)
    EditText myCartNumberAddFunction;
    @BindView(R.id.my_cart_number_brand)
    EditText myCartNumberBrand;
    @BindView(R.id.my_cart_add)
    Button myCartAdd;
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_cart);
        ButterKnife.bind(this);
        setBackBtn();
        setTitle("完善爱车信息");
    }

    @OnClick(R.id.my_cart_add)
    public void onViewClicked() {
        if (TextUtils.isEmpty(myCartNumberAddChejia.getText()) || TextUtils.isEmpty(myCartNumberAddChepai.getText()) ||
                TextUtils.isEmpty(myCartNumberAddFadonji.getText()) || TextUtils.isEmpty(myCartNumberAddDaihao.getText()) ||
                TextUtils.isEmpty(myCartNumberBrand.getText())) {
            ToastUtils.showWarning(this, "请输入完整信息");
        } else {
            postCartInfo();
        }
    }

    private void postCartInfo() {
        MyLoader.showLoading(AddMyCart.this);
        Map<String, String> options = new HashMap<>();
        options.put("type", "input");
        options.put("key", Common.getKey());
        options.put("frame", myCartNumberAddChejia.getText().toString());
        options.put("number", myCartNumberAddChepai.getText().toString());
        options.put("engine", myCartNumberAddFadonji.getText().toString());
        options.put("vin", myCartNumberAddDaihao.getText().toString());
        if (!TextUtils.isEmpty(myCartNumberBrand.getText()))
            options.put("used", myCartNumberAddFunction.getText().toString());
        else
            options.put("used", "未填写");
        options.put("brand", myCartNumberBrand.getText().toString());
        mSubscription = NetWork.postCartInforApi()
                .postCartInfor(options)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel>() {
                    @Override
                    public void onCompleted() {
                        MyLoader.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        MyLoader.stopLoading();
                        Common.ShouwError(AddMyCart.this);
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        MyLoader.stopLoading();
                        if (baseModel != null) {
                            if (baseModel.code == 0) {
                                ToastUtils.showSuccess(AddMyCart.this, "上传成功");
                            } else {
                                ToastUtils.showError(AddMyCart.this, "上传失败，请稍后再试 " + baseModel.hint);
                            }
                        } else {
                            ToastUtils.showError(AddMyCart.this, "上传失败 " + baseModel.code + " " + baseModel.data);
                        }
                    }
                });
    }
}
