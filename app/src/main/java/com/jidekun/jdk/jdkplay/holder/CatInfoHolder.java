package com.jidekun.jdk.jdkplay.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.bean.CategoryInfo;
import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.global.ImageLoaderOptions;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.InjectView;

/**
 * Created by JDK on 2016/6/1.
 */
public class CatInfoHolder extends BaseHolder<Object> {


    private ImageView iv_image1;
    private ImageView iv_image2;
    private ImageView iv_image3;
    private TextView tv_name1;
    private TextView tv_name2;
    private TextView tv_name3;

    @Override
    public void bindData(Object data) {
        CategoryInfo info = (CategoryInfo) data;
        //第一个数据不会发生 不存在的问题
        tv_name1.setText(info.getName1());
        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + info.getUrl1(), iv_image1, ImageLoaderOptions.round_options);
        //获得父布局
        ViewGroup parent2 = (ViewGroup) iv_image2.getParent();
        ViewGroup parent3 = (ViewGroup) iv_image3.getParent();


        //如果第二个和第三个没数据,则隐藏它 ,父布局为linearlayout
        if (TextUtils.isEmpty(info.getName2())) {
            //设置隐藏,不能设置为Gon 因为设置了权重
            parent2.setVisibility(View.INVISIBLE);
        } else {
            //为了防止listview复用条目时,出现的Bug ,将条目置为可显示/否则会出现该显示的布局被隐藏
            parent2.setVisibility(View.VISIBLE);
            tv_name2.setText(info.getName2());
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + info.getUrl2(), iv_image2, ImageLoaderOptions.round_options);
        }

        if (TextUtils.isEmpty(info.getName3())) {
            //设置隐藏,不能设置为Gon 因为设置了权重
            parent3.setVisibility(View.INVISIBLE);
        } else {
            //为了防止listview复用条目时,出现的Bug ,将条目置为可显示/否则会出现该显示的布局被隐藏
            parent3.setVisibility(View.VISIBLE);
            tv_name3.setText(info.getName3());
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + info.getUrl3(), iv_image3, ImageLoaderOptions.round_options);
        }
    }

    @Override
    public View initHolderView() {
        View view = View.inflate(JDKContext.context, R.layout.adapter_category_info, null);
        iv_image1 = (ImageView) view.findViewById(R.id.iv_image1);
        iv_image2 = (ImageView) view.findViewById(R.id.iv_image2);
        iv_image3 = (ImageView) view.findViewById(R.id.iv_image3);
        tv_name1 = (TextView) view.findViewById(R.id.tv_name1);
        tv_name2 = (TextView) view.findViewById(R.id.tv_name2);
        tv_name3 = (TextView) view.findViewById(R.id.tv_name3);
        return view;
    }
}
