package com.jidekun.jdk.jdkplay.adapter;

import com.jidekun.jdk.jdkplay.holder.CatInfoHolder;
import com.jidekun.jdk.jdkplay.holder.CatTitleHolder;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;

import java.util.ArrayList;

/**
 * Created by JDK on 2016/6/1.
 */
public class CatgoryAdapter extends BasicAdapter<Object> {
    //1.定义条目的类型
    private static final int CAT_TITLE = 0;//title类型的条目
    private static final int CAT_INFO = 1;//info类型的条目

    public CatgoryAdapter(ArrayList<Object> list) {
        super(list);
    }
    /**
     * 返回条目类型的总数
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * 返回指定的position的条目是什么类型的
     */
    @Override
    public int getItemViewType(int position) {
        //通过指定position的数据类型来判断条目的类型
        Object o = list.get(position);
        if (o instanceof String) {
            //说明当前条目是title类型的
            return CAT_TITLE;
        } else {
            //说明当前条目是info类型的
            return CAT_INFO;
        }
    }

    @Override
    public BaseHolder<Object> getHolder(int position) {
        int itemViewType = getItemViewType(position);
        //这里泛型应该设置为object
        BaseHolder<Object> baseHolder = null;
        //如果是标题则返回 标题布局的holder ,否则是三个条目的布局的holder
        switch (itemViewType){
            case CAT_TITLE:
                //加载title类型的布局
                //绑定数据到title布局
                //但是由于holder封装了加载布局和绑定数据的操作，所以现在只需要根据不同的条目类型
                //返回不同的holder
                baseHolder= new CatTitleHolder();
                break;
            case CAT_INFO:
                //加载info类型的布局
                //绑定数据到info布局
                baseHolder= new CatInfoHolder();
                break;
        }
        return baseHolder;
    }
}
