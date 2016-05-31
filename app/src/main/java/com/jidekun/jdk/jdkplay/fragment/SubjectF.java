package com.jidekun.jdk.jdkplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jidekun.jdk.jdkplay.adapter.BasicAdapter;
import com.jidekun.jdk.jdkplay.adapter.SubjectAdapter;
import com.jidekun.jdk.jdkplay.bean.AppInfo;
import com.jidekun.jdk.jdkplay.bean.Subject;
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
public class SubjectF extends BaseListFragment<Subject> {


    @Override
    protected BasicAdapter<Subject> getAdapter() {
        return new SubjectAdapter(list);
    }

    /**
     * 向服务器请求数据
     */
    @Override
    public Object requstData() {
        checkPullFromStart();
        //返回json字符串 如果集合size为0 为第一次0到20的数据
        String result = HttpUtil.get(Api.Subject + list.size());

        //使用工具类 将json转集合
        ArrayList<Subject> subjects = (ArrayList<Subject>) JsonUtil.parseJsonToList(result, new TypeToken<List<Subject>>() {
        }.getType());
        if (subjects != null) {
            list.addAll(subjects);//更新数据
            CommonUtils.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    basicAdapter.notifyDataSetChanged();//更新ui 主线程
                    //完成刷新,关闭刷新
                    refreshListView.onRefreshComplete();
                }
            });
        }
        return subjects;
    }
}
