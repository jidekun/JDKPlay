package com.jidekun.jdk.jdkplay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;

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
        BaseHolder<T> holder = null;
        if (convertView == null) {
            holder = getHolder(position);
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        //绑定数据的操作在ViewHolder中进行
        holder.bindData(list.get(position));
        //从holderview中获取view
        View holderView = holder.getHolderView();
        // 4.增加炫酷动画
        ScaleAnimation animation = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(400);
        animation.setInterpolator(new OvershootInterpolator());
        holderView.startAnimation(animation);

        // 一开始缩小
        //如下是使用Nineoldeandroid.jar 向下兼容包
//        holderView.setScaleX(0.5f);
//        holderView.setScaleY(0.5f);
        // 执行放大动画
        //如下是使用Nineoldeandroid.jar 向下兼容包
//        ViewPropertyAnimator.animate(holder.getHolderView()).
//                scaleX(1f).
//                setDuration(400).
//                setInterpolator(new OvershootInterpolator()).
//                start();
//        ViewPropertyAnimator.animate(holder.getHolderView()).
//                scaleY(1f).
//                setDuration(400)
//                .setInterpolator(new OvershootInterpolator()).
//                start();
        return holderView;
    }

    public abstract BaseHolder<T> getHolder(int position);
}
