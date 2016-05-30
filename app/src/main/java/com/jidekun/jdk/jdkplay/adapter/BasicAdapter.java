package com.jidekun.jdk.jdkplay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.jidekun.jdk.jdkplay.holder.HomeViewHolder;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;

import java.util.ArrayList;

/**
 * Created by JDK on 2016/5/30.
 */
public abstract class BasicAdapter<T> extends BaseAdapter {
    protected ArrayList<T> list;

    public BasicAdapter(ArrayList<T> list) {
        super();
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //初始化holder,将View对象所有的操作都交给Viewholder去做,自己只返回数据
        BaseHolder<T> holder =null;
        if (convertView == null) {
            holder = getHolder();
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        //绑定数据的操作在ViewHolder中进行
        holder.bindData(list.get(position));
        //从holderview中获取view
        View holderView = holder.getHolderView();
        return holderView;
    }
    public abstract  BaseHolder<T> getHolder();
}
