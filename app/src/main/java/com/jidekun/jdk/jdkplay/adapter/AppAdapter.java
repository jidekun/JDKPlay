package com.jidekun.jdk.jdkplay.adapter;

import com.jidekun.jdk.jdkplay.bean.AppInfo;
import com.jidekun.jdk.jdkplay.holder.HomeViewHolder;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;

import java.util.ArrayList;

/**
 * Created by JDK on 2016/5/31.
 */
public class AppAdapter extends BasicAdapter<AppInfo>{
    public AppAdapter(ArrayList<AppInfo> list) {
        super(list);
    }

    @Override
    public BaseHolder<AppInfo> getHolder(int position) {
        //因为显示的item和home界面的一样直接使用home界面的
        return new HomeViewHolder();
    }
}
