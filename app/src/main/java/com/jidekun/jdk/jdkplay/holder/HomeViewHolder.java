package com.jidekun.jdk.jdkplay.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.bean.AppInfo;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;

/**
 * Created by JDK on 2016/5/30.
 */
public  class HomeViewHolder extends BaseHolder<AppInfo>{
    ImageView iv_icon;
    RatingBar rb_star;
    TextView tv_name,tv_size,tv_des;

     //init holderview
    //进一步抽取 ,为抽取ViewHolder做准备
    public View initHolderView() {
        View view = View.inflate(JDKContext.context, R.layout.adapter_home, null);
        iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        rb_star = (RatingBar) view.findViewById(R.id.rb_star);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_size = (TextView) view.findViewById(R.id.tv_size);
        tv_des = (TextView) view.findViewById(R.id.tv_des);
        return view;
    }

    // bind data

    public void bindData(AppInfo appInfo) {
        tv_name.setText(appInfo.getName());
        rb_star.setRating(appInfo.getStars());
        tv_size.setText(Formatter.formatFileSize(JDKContext.context,appInfo.getSize()));
        tv_des.setText(appInfo.getDes());
    }
}
