package org.fireking.universal_chart.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.fireking.universal_chart.bean.CategoryBean;

import java.util.List;

/**
 * Created by wangg on 2016/1/8.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private List<CategoryBean> mBeanList;

    public MainPagerAdapter(FragmentManager fm, List<CategoryBean> beanList) {
        super(fm);
        this.mBeanList = beanList;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return mBeanList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBeanList.get(position).getName_zh();
    }
}
