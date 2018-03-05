package com.rongyuan.mingyida.module.me.account;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ChangePswActivity extends BaseActivity {

    @BindView(R.id.et_changepsw_psw)
    EditText etChangepswPsw;
    @BindView(R.id.et_changepsw_newpsw)
    EditText etChangepswNewpsw;
    @BindView(R.id.et_changepsw_newpsw_again)
    EditText etChangepswNewpswAgain;
    @BindView(R.id.btn_changepsw_ok)
    Button btnChangepswOk;
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_psw);
        ButterKnife.bind(this);
        setTitle("修改密码");
        setBackBtn();
    }

    private void postNewPsw() {
        MyLoader.showLoading(ChangePswActivity.this);
        Map<String, String> options = new HashMap<>();
        options.put("type", "update_password");
        options.put("key", Common.getKey());
        options.put("password", etChangepswNewpsw.getText().toString());
        options.put("old_password", etChangepswPsw.getText().toString());
        mSubscription = NetWork.getForgetPswApi()
                .getForgetPsw(options)
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
                        Common.ShouwError(ChangePswActivity.this);
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        MyLoader.stopLoading();
                        if (baseModel != null) {
                            if (baseModel.code == 0) {
                                ToastUtils.showSuccess(ChangePswActivity.this, "修改成功");
                            }else if(baseModel.code == 1000){
                                ToastUtils.showWarning(ChangePswActivity.this, "旧密码错误 ");
                            } else {
                                ToastUtils.showError(ChangePswActivity.this, "修改失败，请稍后再试 " + baseModel.hint);
                            }
                        } else {
                            Common.ShouwError(ChangePswActivity.this);
                        }
                    }
                });
    }

    @OnClick(R.id.btn_changepsw_ok)
    public void onViewClicked() {
        if (!TextUtils.isEmpty(etChangepswPsw.getText()) && !TextUtils.isEmpty(etChangepswNewpsw.getText())) {
            if (etChangepswNewpsw.getText().toString().equals(etChangepswNewpswAgain.getText().toString())) {
                postNewPsw();
            } else {
                ToastUtils.showWarning(this, "两次密码不一致");
            }
        } else {
            ToastUtils.showWarning(this, "请输入完整信息");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
