package com.jidekun.jdk.jdkplay.holder.base;
import android.view.View;
/**
 * Created by JDK on 2016/5/30.
 */
public abstract class BaseHolder<T> {
    private View HolderView;

    //构造方法
    public BaseHolder() {
        HolderView = initHolderView();
        HolderView.setTag(this);
    }
    //体现封装
    public View getHolderView() {
        return HolderView;
    }
    //让自己去实现绑定自己特有的数据
    public abstract void bindData(T data);
    //让子类自己去实现属于自己的holderview
    public abstract View initHolderView();
}
