package com.jidekun.jdk.jdkplay.adapter;
import com.jidekun.jdk.jdkplay.bean.AppInfo;
import com.jidekun.jdk.jdkplay.holder.HomeViewHolder;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;
import java.util.ArrayList;

/**
 * Created by JDK on 2016/5/30.
 */
public class HomeAdapter extends BasicAdapter<AppInfo> {
    public HomeAdapter(ArrayList<AppInfo> list) {
        //将集合传给父类之后,自己也能访问到该集合
        super(list);
    }

    @Override
    public BaseHolder<AppInfo> getHolder() {
        return new HomeViewHolder();
    }
}
