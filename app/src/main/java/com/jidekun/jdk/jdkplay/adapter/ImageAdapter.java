package com.jidekun.jdk.jdkplay.adapter;

import android.view.ViewGroup;

import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.global.ImageLoaderOptions;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by JDK on 2016/6/3.
 */
public class ImageAdapter extends BasePagerAdapter<String> {
    public ImageAdapter(ArrayList<String> list) {
        super(list);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //使用第三方框架,缩放图片,用来替代imageview
        PhotoView pt = new PhotoView(JDKContext.context);
        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + list.get(position), pt, ImageLoaderOptions.fadein_options);
        container.addView(pt);
        return pt;
    }
}
