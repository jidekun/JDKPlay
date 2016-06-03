package com.jidekun.jdk.jdkplay.holder;

import android.view.View;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.bean.AppInfo;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;

/**
 * Created by JDK on 2016/6/4.
 */
public class Detail_Down_Holder  extends BaseHolder<AppInfo>{
    @Override
    public void bindData(AppInfo data) {

    }

    @Override
    public View initHolderView() {
        View view =View.inflate(JDKContext.context, R.layout.detail_down,null);
        return view;
    }
}
