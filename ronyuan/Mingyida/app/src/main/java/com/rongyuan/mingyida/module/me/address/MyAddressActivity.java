package com.rongyuan.mingyida.module.me.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rongyuan.mingyida.R;
import com.rongyuan.mingyida.base.BaseActivity;
import com.rongyuan.mingyida.model.MyAddressModel;
import com.rongyuan.mingyida.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAddressActivity extends BaseActivity {

    @BindView(R.id.recycler_my_address)
    RecyclerView recyclerMyAddress;
    @BindView(R.id.tv_add_my_address)
    TextView tvAddMyAddress;
    MyAddressAdapter mAddressAdapter;
    List<MyAddressModel> AdressData;
    Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        ButterKnife.bind(this);
        setBackBtn();
        setTitle("我的地址");
        setRecyclerData();
        setRecyclerView();
    }

    private void setRecyclerData() {

        //todo post

        AdressData = new ArrayList<>();
        for (int i = 0; i<6 ;i++){
            MyAddressModel data = new MyAddressModel();
            data.setName("呀呀");
            data.setPhone("18408262666");
            if (i ==2){
                data.setIsdefault(true);
            }else {
                data.setIsdefault(false);
            }
            data.setAddress("成都 武侯 天府五街");
            data.setDetale_address("育才竹岛");
            AdressData.add(data);
        }
    }

    private void setRecyclerView() {

        if (AdressData!=null){
            mAddressAdapter = new MyAddressAdapter(AdressData);
            mAddressAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

            mAddressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()){
                        case R.id.tv_my_address_editor:
                            intent = new Intent(MyAddressActivity.this,EditeAddressActivity.class);
                            intent.putExtra("addressTitle","修改地址");
                            startActivity(intent);
                            break;
                        case R.id.tv_my_address_delete:
                            ToastUtils.showUsual(MyAddressActivity.this,"delete");
                            break;
                    }
                }
            });
            mAddressAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    for (int i = 0;i<AdressData.size();i++){
                        if (i!=position){
                            AdressData.get(i).setIsdefault(false);
                        }else {
                            AdressData.get(i).setIsdefault(true);
                        }
                    }
                    mAddressAdapter.notifyDataSetChanged();
                    //todo  post
                }
            });
            recyclerMyAddress.setLayoutManager(new LinearLayoutManager(this));
            recyclerMyAddress.setAdapter(mAddressAdapter);
        }

    }

    @OnClick(R.id.tv_add_my_address)
    public void onViewClicked() {
        intent = new Intent(MyAddressActivity.this,EditeAddressActivity.class);
        intent.putExtra("addressTitle","添加地址");
        startActivity(intent);
    }
}
