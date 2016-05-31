package com.jidekun.jdk.jdkplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jidekun.jdk.jdkplay.adapter.AppAdapter;
import com.jidekun.jdk.jdkplay.adapter.BasicAdapter;
import com.jidekun.jdk.jdkplay.adapter.HomeHeaderAdapter;
import com.jidekun.jdk.jdkplay.bean.AppInfo;
import com.jidekun.jdk.jdkplay.bean.Home;
import com.jidekun.jdk.jdkplay.fragment.base.BaseFragment;
import com.jidekun.jdk.jdkplay.fragment.base.BaseListFragment;
import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.http.HttpUtil;
import com.jidekun.jdk.jdkplay.utils.CommonUtils;
import com.jidekun.jdk.jdkplay.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JDK on 2016/5/27.
 */
public class AppF extends BaseListFragment<AppInfo> {


    @Override
    protected BasicAdapter<AppInfo> getAdapter() {
        return new AppAdapter(list);
    }

    /**
     * 向服务器请求数据
     */
    @Override
    public Object requstData() {
        checkPullFromStart();
        //返回json字符串 如果集合size为0 为第一次0到20的数据
        String result = HttpUtil.get(Api.App + list.size());
        //使用工具类 将json转集合
        ArrayList<AppInfo> appInfos = (ArrayList<AppInfo>) JsonUtil.parseJsonToList(result, new TypeToken<List<AppInfo>>() {
        }.getType());
        if (appInfos != null) {
            list.addAll(appInfos);//更新数据
            CommonUtils.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    basicAdapter.notifyDataSetChanged();//更新ui 主线程
                    //完成刷新,关闭刷新
                    refreshListView.onRefreshComplete();
                }
            });
        }
        return appInfos;
    }
}
