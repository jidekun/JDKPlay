package com.jidekun.jdk.jdkplay.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.global.ImageLoaderOptions;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by JDK on 2016/5/31.
 */
public class HomeHeaderAdapter extends BasePagerAdapter<String> {
     public HomeHeaderAdapter(ArrayList<String> list) {
        super(list);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv =new ImageView(JDKContext.context);
        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX+list.get(position%list.size()),iv, ImageLoaderOptions.fadein_options);
        container.addView(iv);
        return iv;
    }
}
