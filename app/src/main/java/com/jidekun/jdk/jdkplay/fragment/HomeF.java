package com.jidekun.jdk.jdkplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.adapter.HomeAdapter;
import com.jidekun.jdk.jdkplay.bean.AppInfo;
import com.jidekun.jdk.jdkplay.bean.Home;
import com.jidekun.jdk.jdkplay.fragment.base.BaseFragment;
import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.jidekun.jdk.jdkplay.http.HttpUtil;
import com.jidekun.jdk.jdkplay.utils.CommonUtils;

import java.util.ArrayList;


/**
 * Created by JDK on 2016/5/27.
 */
public class HomeF extends BaseFragment {
    private ArrayList<String> picture;//首页轮播图的url
    private ArrayList<AppInfo> list = new ArrayList<>();
    private ListView listView;
    private HomeAdapter homeAdapter;


    @Override
    public View createView() {
        listView = (ListView) View.inflate(JDKContext.context, R.layout.listview, null);
        homeAdapter = new HomeAdapter(list);
        listView.setAdapter(homeAdapter);

        return listView;
    }

    @Override
    public Object requstData() {
        //返回json字符串
        String s = HttpUtil.get(Api.HOME);
        //使用jsonutils,将json转换成对象
        //Home home = JsonUtil.parseJsonToBean(s, Home.class);
        Gson gson = new Gson();
        Home home = gson.fromJson(s, Home.class);
        if (home != null) {
            list.addAll(home.getList());//更新数据
            CommonUtils.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    homeAdapter.notifyDataSetChanged();//更新ui 主线程
                }
            });
        }
        return home;
    }


}
