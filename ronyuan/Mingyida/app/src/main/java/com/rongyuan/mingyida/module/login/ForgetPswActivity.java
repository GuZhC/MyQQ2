package com.rongyuan.mingyida.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rongyuan.mingyida.R;
import com.rongyuan.mingyida.base.BaseActivity;
import com.rongyuan.mingyida.model.BaseModel;
import com.rongyuan.mingyida.net.NetWork;
import com.rongyuan.mingyida.ui.MyLoader;
import com.rongyuan.mingyida.utils.Common;
import com.rongyuan.mingyida.utils.ToastUtils;
import com.rongyuan.mingyida.utils.countDownTimer;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ForgetPswActivity extends BaseActivity {

    @BindView(R.id.et_fogetpsw_phone)
    EditText etFogetpswPhone;
    @BindView(R.id.et_fogetpsw_code)
    EditText etFogetpswCode;
    @BindView(R.id.tv_fogetpsw_getcode)
    TextView tvFogetpswGetcode;
    @BindView(R.id.et_fogetpsw_psw)
    EditText etFogetpswPsw;
    @BindView(R.id.et_fogetpsw_pswtwo)
    EditText etFogetpswPswtwo;
    @BindView(R.id.btn_fogetpsw_ok)
    Button btnFogetpswOk;
    private Subscription mSubscription;
    private String role = "member";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_psw);
        ButterKnife.bind(this);
        setBackBtn();
        setTitle("忘记密码");
        Intent intent = getIntent();
        role = intent.getStringExtra("which");
    }

    @OnClick({R.id.tv_fogetpsw_getcode, R.id.btn_fogetpsw_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_fogetpsw_getcode:
                if (!TextUtils.isEmpty(etFogetpswPhone.getText().toString())) {
                    new countDownTimer<TextView>(tvFogetpswGetcode);
                    getCode();
                } else {
                    ToastUtils.showWarning(this, "请输入手机号");
                }
                break;
            case R.id.btn_fogetpsw_ok:
                if (TextUtils.isEmpty(etFogetpswPhone.getText().toString()) || TextUtils.isEmpty(etFogetpswCode.getText().toString())
                        || TextUtils.isEmpty(etFogetpswPsw.getText().toString()) || TextUtils.isEmpty(etFogetpswPswtwo.getText().toString())) {
                    ToastUtils.showWarning(this, "请完整填写信息");
                } else {
                    if (etFogetpswPsw.getText().toString().equals(etFogetpswPswtwo.getText().toString())) {
                        postNewPsw();
                    } else {
                        ToastUtils.showWarning(this, "两次密码不一致");
                    }
                }
                break;
        }
    }

    private void postNewPsw() {
        MyLoader.showLoading(ForgetPswActivity.this);
        Map<String, String> options = new HashMap<>();
        options.put("type", "forget_password");
        options.put("which", role);
        options.put("username", etFogetpswPhone.getText().toString());
        options.put("password", etFogetpswPsw.getText().toString());
        options.put("auth_code", etFogetpswCode.getText().toString());
        options.put("auth_code_type", "forget_password_xyz");
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
                        Common.ShouwError(ForgetPswActivity.this);
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        MyLoader.stopLoading();
                        if (baseModel != null) {
                            if (baseModel.code == 0) {
                                ToastUtils.showSuccess(ForgetPswActivity.this, "修改成功");
                            } else if (baseModel.code == 10003) {
                                ToastUtils.showError(ForgetPswActivity.this, "验证码错误");
                            } else {
                                ToastUtils.showError(ForgetPswActivity.this, "修改失败，请稍后再试");
                            }

                        } else {
//                            ToastUtils.showError(ForgetPswActivity.this,baseModel.code +"  "+baseModel.data);
                            Common.ShouwError(ForgetPswActivity.this);
                        }
                    }
                });
    }

    private void getCode() {
        mSubscription = NetWork.getCodeApi()
                .getCode(etFogetpswPhone.getText().toString(), "forget_password_xyz")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Common.ShouwError(ForgetPswActivity.this);
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel != null) {
                            if (baseModel.code == 0) {
                                ToastUtils.showSuccess(ForgetPswActivity.this, "发送成功，请注意查收");
                            } else {
                                ToastUtils.showError(ForgetPswActivity.this, "发送失败，请稍后再试");
                            }

                        } else {
                            Common.ShouwError(ForgetPswActivity.this);
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
