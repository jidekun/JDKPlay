package com.jidekun.jdk.jdkplay.fragment;

import android.os.Bundle;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jidekun.jdk.jdkplay.fragment.base.BaseFragment;
import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.http.HttpUtil;
import com.jidekun.jdk.jdkplay.utils.LogUtil;


/**
 * Created by JDK on 2016/5/27.
 */
public class HomeF extends BaseFragment {


    @Override
    public Object requstData() {
        //返回json字符串
        String s = HttpUtil.get(Api.HOME);
        //使用jsonutils
        return s;
    }
}
