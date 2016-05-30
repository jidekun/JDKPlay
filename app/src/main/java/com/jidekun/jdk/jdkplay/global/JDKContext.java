package com.jidekun.jdk.jdkplay.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.jidekun.jdk.jdkplay.utils.LogUtil;

/**
 * Created by JDK on 2016/5/28.
 */
public class JDKContext extends Application {
    public static Context context;
    public static  Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化一个全局变量
        context=this;
        //初始化一个主线程Handler
        handler =new Handler();

    }
}
