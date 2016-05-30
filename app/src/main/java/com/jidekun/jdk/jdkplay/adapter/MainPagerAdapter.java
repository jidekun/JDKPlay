package com.jidekun.jdk.jdkplay.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.fragment.FragmentFactory;
import com.jidekun.jdk.jdkplay.utils.CommonUtils;

/**
 * Created by JDK on 2016/5/27.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private String [] tabs;
    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        //获得String.xml中的数组
        tabs = CommonUtils.getStringArray(R.array.tab_names);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.create(position);
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
