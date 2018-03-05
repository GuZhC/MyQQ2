package com.rongyuan.mingyida.module.login;

import android.content.Context;
import android.content.SharedPreferences;

import com.rongyuan.mingyida.model.BaseModel;
import com.rongyuan.mingyida.model.LoginModel;
import com.rongyuan.mingyida.net.NetWork;
import com.rongyuan.mingyida.utils.Common;
import com.rongyuan.mingyida.utils.Eds;
import com.rongyuan.mingyida.utils.ToastUtils;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by guZhongC on 2018/2/5.
 * describe:
 */

public class LoginPresenter implements LoginContract.ILoginPresentr {
    private final LoginContract.ILoginView mLoginView;
    private Subscription mSubscription;
    private SharedPreferences sp;
    Context context;


    LoginPresenter(LoginContract.ILoginView loginView, Context context) {
        this.mLoginView = loginView;
        this.context = context;
        sp = context.getSharedPreferences("User", Context.MODE_PRIVATE);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public String getName() {
        try {
            return Eds.getDESOri(sp.getString("name", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String getPsw() {
        try {
            return Eds.getDESOri(sp.getString("psw", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public boolean isRemener() {
        return sp.getBoolean("isRemember", false);
    }

    @Override
    public boolean isMember() {
        return sp.getBoolean("isMember", true);
    }

    @Override
    public void Login(String type, String name, String psw) {
        mLoginView.ShowLoading();
        mSubscription = NetWork.getLoginApi()
                .getLoginData(type, name, psw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<LoginModel>>() {
                    @Override
                    public void onCompleted() {
                        mLoginView.StopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginView.StopLoading();
                        Common.ShouwError(context);
                    }

                    @Override
                    public void onNext(BaseModel<LoginModel> loginModel) {
                        mLoginView.StopLoading();
                        if (loginModel != null ) {
                            if (loginModel.code == 0) {
                                if (loginModel.getData() != null){
                                    SharedPreferences.Editor edit = sp.edit();
                                    try {
                                        edit.putString("key", Eds.getDES(loginModel.getData().getApi_secret()));
                                        edit.commit();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    mLoginView.ActivityFinish();
                                }else {
                                    ToastUtils.showError(context,"获取用户信息失败");
                                }
                            } else if (loginModel.code == 10003) {
                                ToastUtils.showError(context,"密码错误");
                            } else if (loginModel.code == 10006) {
                                ToastUtils.showError(context,"用户不存在，或角色错误");
                            } else {
                                ToastUtils.showError(context, String.valueOf(loginModel.hint));
                            }
                        } else {
                            Common.ShouwError(context);
                        }
                    }
                });
    }

    @Override
    public void RememberPsw(boolean isRemember, String name, String psw, boolean isMember) throws Exception {
        if (name == null || psw == null)
            return;
        if (isRemember) {
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("name", Eds.getDES(name));
            edit.putString("psw", Eds.getDES(psw));
            edit.putBoolean("isRemember", isRemember);
            edit.putBoolean("isMember", isMember);
            edit.commit();
        } else {
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("name", "");
            edit.putString("psw", "");
            edit.putBoolean("isRemember", isRemember);
            edit.putBoolean("isMember", true);
            edit.commit();
        }
    }
}
