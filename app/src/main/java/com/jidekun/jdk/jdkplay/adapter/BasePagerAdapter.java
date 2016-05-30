package com.jidekun.jdk.jdkplay.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by JDK on 2016/5/31.
 */
public class BasePagerAdapter<T> extends PagerAdapter {
    ArrayList<T> list;

    public BasePagerAdapter(ArrayList<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return 1000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
