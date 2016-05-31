package com.jidekun.jdk.jdkplay.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.bean.Subject;
import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.global.ImageLoaderOptions;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by JDK on 2016/5/31.
 */
public class SubjectViewHolder extends BaseHolder<Subject> {
    private ImageView iv_image;
    private TextView tv_des;
    @Override
    public View initHolderView() {
        View view = View.inflate(JDKContext.context, R.layout.adapter_subject, null);
        iv_image = (ImageView) view.findViewById(R.id.iv_image);
        tv_des = (TextView) view.findViewById(R.id.tv_des);
        return view;
    }

    @Override
    public void bindData(Subject data) {
        tv_des.setText(data.getDes());
        //1.获取屏幕的宽度
        //2.根据图片的宽高比计算对应的高度
        //3.将高度设置给ImageView
        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX+data.getUrl(),iv_image, ImageLoaderOptions.fadein_options);
    }
}
