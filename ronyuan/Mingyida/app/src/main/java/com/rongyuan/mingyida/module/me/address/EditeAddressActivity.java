package com.rongyuan.mingyida.module.me.address;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.rongyuan.mingyida.R;
import com.rongyuan.mingyida.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditeAddressActivity extends BaseActivity {

    @BindView(R.id.et_edit_address_name)
    EditText etEditAddressName;
    @BindView(R.id.et_edit_address_phone)
    EditText etEditAddressPhone;
    @BindView(R.id.tv_edit_address)
    TextView tvEditAddress;
    @BindView(R.id.et_edit_address_detail)
    EditText etEditAddressDetail;
    @BindView(R.id.cb_edit_address_default)
    CheckBox cbEditAddressDefault;
    @BindView(R.id.btn_edit_address_ok)
    Button btnEditAddressOk;
    CityPickerView mPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_address);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        setBackBtn();
        mPicker = new CityPickerView();
        mPicker.init(this);
        if (intent != null) {
            setTitle(intent.getStringExtra("addressTitle"));
        } else {
            setTitle(intent.getStringExtra("地址管理"));
        }
    }

    @OnClick({R.id.tv_edit_address, R.id.btn_edit_address_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_edit_address:
                choseAddress();
                break;
            case R.id.btn_edit_address_ok:
                break;
        }
    }

    private void choseAddress() {
        //添加配置
        CityConfig cityConfig = new CityConfig.Builder()
                .confirTextColor("#00bcd4")//确认按钮文字颜色
                .province("四川省")//默认显示的省份
                .city("成都市")//默认显示省份下面的城市
                .district("武侯区")//默认显示省市下面的区县数据
                .build();
        mPicker.setConfig(cityConfig);

//监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                if (province != null && city != null && district != null) {
                    tvEditAddress.setText(province+","+city+","+district);
                }
            }
            @Override
            public void onCancel() {
//                ToastUtils.showError(EditeAddressActivity.this,"修改失败，请稍后再试");
            }
        });

        //显示
        mPicker.showCityPicker();
    }
}
