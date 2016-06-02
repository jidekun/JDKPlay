package com.jidekun.jdk.jdkplay.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.fragment.base.BaseFragment;
import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.http.HttpUtil;
import com.jidekun.jdk.jdkplay.ui.FlowLayout;
import com.jidekun.jdk.jdkplay.utils.ColorUtil;
import com.jidekun.jdk.jdkplay.utils.CommonUtils;
import com.jidekun.jdk.jdkplay.utils.DrawableUtil;
import com.jidekun.jdk.jdkplay.utils.JsonUtil;
import com.jidekun.jdk.jdkplay.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JDK on 2016/5/27.
 */
public class HotF extends BaseFragment {


    private FlowLayout flowLayout;

    @Override
    public View createView() {
        View view = View.inflate(getActivity(), R.layout.hot_view, null);
        flowLayout = (FlowLayout) view.findViewById(R.id.fl);
        flowLayout.setHorizontalSpacing(50);
        flowLayout.setVerticalSpacing(50);
        return view;
    }

    @Override
    public Object requstData() {
        String result = HttpUtil.get(Api.Hot);
        //将json解析成字符串集合
        final ArrayList<String> list =
                (ArrayList<String>) JsonUtil.parseJsonToList(result, new TypeToken<List<String>>() {
                }.getType());
        if (list != null) {
            CommonUtils.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < list.size(); i++) {
                        final TextView tv = new TextView(getActivity());
                        tv.setTextSize(16);
                        tv.setGravity(Gravity.CENTER);
                        tv.setText(list.get(i));
                        tv.setTextColor(Color.WHITE);
                        tv.setPadding(9,6, 9, 6);
                        Drawable pressed = DrawableUtil.generateDrawable(ColorUtil.randomColor(),6);
                        Drawable normal = DrawableUtil.generateDrawable(ColorUtil.randomColor(),9);
                        tv.setBackgroundDrawable(DrawableUtil.generateSelector(pressed, normal));

                        flowLayout.addView(tv);
                        //设置点击事件
                        tv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showToast(tv.getText().toString());
                            }
                        });
                    }
                }
            });

        }

        return list;
    }
}
