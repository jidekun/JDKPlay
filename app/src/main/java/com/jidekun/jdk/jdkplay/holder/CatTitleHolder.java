package com.jidekun.jdk.jdkplay.holder;

import android.view.View;
import android.widget.TextView;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;

/**
 * Created by JDK on 2016/6/1.
 */
public class CatTitleHolder extends BaseHolder<Object>{

    private TextView tv;



    @Override
    public void bindData(Object data) {
        tv.setText((String) data);
    }

    @Override
    public View initHolderView() {
        View view =View.inflate(JDKContext.context, R.layout.adapter_category_title,null);
        tv = (TextView) view.findViewById(R.id.tv_title);
        return view;
    }
}
