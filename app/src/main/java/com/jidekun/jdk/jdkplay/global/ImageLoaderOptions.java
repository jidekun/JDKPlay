package com.jidekun.jdk.jdkplay.global;

import android.graphics.Bitmap;

import com.jidekun.jdk.jdkplay.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

//ImageLoader的第三个参数
public interface ImageLoaderOptions {
    //渐渐显示的动画
    DisplayImageOptions fadein_options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.ic_default)// 设置加载中显示什么图片
            .showImageForEmptyUri(R.drawable.ic_default)// 设置如果url为空中显示什么图片
            .showImageOnFail(R.drawable.ic_default)// 设置加载失败显示什么图片
            .cacheInMemory(false)// 不在内存缓存
            .cacheOnDisk(true)// 在硬盘缓存
            .imageScaleType(ImageScaleType.EXACTLY)//内部会对图片进一步的压缩
            .bitmapConfig(Bitmap.Config.RGB_565)//使用比较节省内存的颜色模式
            // .considerExifParams(true)//会识别图片的方向信息
            .displayer(new FadeInBitmapDisplayer(1000)).build();// 设置渐渐显示的动画效果
             // .displayer(new RoundedBitmapDisplayer(100)).build();// 设置圆角显示

    //带圆角的显示效果
    DisplayImageOptions round_options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.ic_default)// 设置加载中显示什么图片
            .showImageForEmptyUri(R.drawable.ic_default)// 设置如果url为空中显示什么图片
            .showImageOnFail(R.drawable.ic_default)// 设置加载失败显示什么图片
            .cacheInMemory(false)// 不在内存缓存 可以减少内存泄露
            .cacheOnDisk(true)// 在硬盘缓存
            .imageScaleType(ImageScaleType.EXACTLY)//内部会对图片进一步的压缩
            .bitmapConfig(Bitmap.Config.RGB_565)//使用比较节省内存的颜色模式
            // .considerExifParams(true)//会识别图片的方向信息
            // .displayer(new FadeInBitmapDisplayer(1000)).build();//设置渐渐显示的动画效果
            .displayer(new RoundedBitmapDisplayer(16)).build();// 设置圆角显示 100为正圆
}
