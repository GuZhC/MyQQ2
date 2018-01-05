package com.rongyuan.mingyida.module.mains;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.rongyuan.mingyida.R;
import com.rongyuan.mingyida.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {
    BottomNavigationBar mBottomNavigationBar;
    ViewPager mViewpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        mViewpage = findViewById(R.id.viewPager);
        setmBottomNavigationBar();
    }

    private void setmBottomNavigationBar() {
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC );
        mBottomNavigationBar //值得一提，模式跟背景的设置都要在添加tab前面，不然不会有效果。
                .setActiveColor(R.color.colorPrimary)//选中颜色 图标和文字
//                .setInActiveColor("#8e8e8e")//默认未选择颜色
                .setBarBackgroundColor(R.color.white);//默认背景色
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.main_icon_one,R.string.tab_one).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.main_icon_two, R.string.tab_two).setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.mipmap.main_icon_three,R.string.tab_three).setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.main_icon_four, R.string.tab_four).setActiveColorResource(R.color.brown))
                .setFirstSelectedPosition(0)//设置默认选择的按钮
                .initialise();//所有的设置需在调用该方法前完成
        mViewpage.setCurrentItem(0);
        mBottomNavigationBar //设置lab点击事件
                .setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position) {
                        mViewpage.setCurrentItem(position);
                        ToastUtils.showUsual(MainActivity.this,"usual");
                    }
                    @Override
                    public void onTabUnselected(int position) {
                    }
                    @Override
                    public void onTabReselected(int position) {
                    }
                });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(BaseFragment.newInstance("新闻"));
        adapter.addFragment(BaseFragment.newInstance("图书"));
        adapter.addFragment(BaseFragment.newInstance("发现"));
        adapter.addFragment(BaseFragment.newInstance("更多"));
        viewPager.setAdapter(adapter);
    }

}
