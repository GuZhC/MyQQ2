package com.mvpstudy;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mvpstudy.api.DoubanManager;
import com.mvpstudy.book.BooksFragment;
import com.mvpstudy.movie.MoviesContract;
import com.mvpstudy.movie.MoviesFragment;
import com.mvpstudy.movie.MoviesPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static  final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.douban_view_pager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.douban_sliding_tabs);

        if (tabLayout !=null){
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void setupViewPager(ViewPager viewPager){
        DoubanPagerAdapter pagerAdapter = new DoubanPagerAdapter(getSupportFragmentManager());
        MoviesFragment moviesFragment = MoviesFragment.newInstance();

        pagerAdapter.addFragment(moviesFragment,getApplication().getResources().getString(R.string.tab_movies_fragment));
        pagerAdapter.addFragment(new BooksFragment(),getApplication().getResources().getString(R.string.tab_books_fragment));
        viewPager.setAdapter(pagerAdapter);

        createrPresenter(moviesFragment);
    }

    private void createrPresenter(MoviesContract.View fragmentView){
        new MoviesPresenter(DoubanManager.createDoubanService(),fragmentView);
    }

    static class DoubanPagerAdapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public DoubanPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment,String title){
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
