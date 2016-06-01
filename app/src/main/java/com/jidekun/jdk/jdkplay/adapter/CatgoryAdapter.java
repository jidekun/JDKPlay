package com.jidekun.jdk.jdkplay.adapter;

import com.jidekun.jdk.jdkplay.holder.CatInfoHolder;
import com.jidekun.jdk.jdkplay.holder.CatTitleHolder;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;

import java.util.ArrayList;

/**
 * Created by JDK on 2016/6/1.
 */
public class CatgoryAdapter extends BasicAdapter<Object> {
    private static final int CAT_TITLE = 0;
    private static final int CAT_INFO = 1;

    public CatgoryAdapter(ArrayList<Object> list) {
        super(list);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    //判断类型 是catgoryinfo信息还是标题
    @Override
    public int getItemViewType(int position) {
        Object o = list.get(position);
        if (o instanceof String) {
            return CAT_TITLE;
        } else {
            return CAT_INFO;
        }
    }

    @Override
    public BaseHolder<Object> getHolder(int position) {
        int itemViewType = getItemViewType(position);
        //这里泛型应该设置为object
        BaseHolder<Object> baseHolder = null;
        switch (itemViewType){
            case CAT_TITLE:
                baseHolder= new CatTitleHolder();
                break;
            case CAT_INFO:
                baseHolder= new CatInfoHolder();
                break;
        }
        return baseHolder;
    }
}
