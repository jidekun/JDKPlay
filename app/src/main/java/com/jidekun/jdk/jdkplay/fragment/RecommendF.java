package com.jidekun.jdk.jdkplay.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.fragment.base.BaseFragment;
import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.http.HttpUtil;
import com.jidekun.jdk.jdkplay.ui.randomlayou.StellarMap;
import com.jidekun.jdk.jdkplay.utils.CommonUtils;
import com.jidekun.jdk.jdkplay.utils.JsonUtil;
import com.jidekun.jdk.jdkplay.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by JDK on 2016/5/27.
 */
public class RecommendF extends BaseFragment {

    private ArrayList<String> list;
    private StellarMap stellarMap;

    @Override
    public View createView() {
        stellarMap = new StellarMap(getActivity());
        //1.设置内容距四周的距离
        int innerPadding = CommonUtils.getDimens(R.dimen.dp15);
        stellarMap.setInnerPadding(innerPadding, innerPadding, innerPadding, innerPadding);
        return stellarMap;
    }

    @Override
    public Object requstData() {
        String result = HttpUtil.get(Api.Recommend);
        list = (ArrayList<String>) JsonUtil.parseJsonToList(result, new TypeToken<List<String>>() {
        }.getType());

        if (list != null) {
            CommonUtils.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    //更新UI
                    stellarMap.setAdapter(new StellarMapAdapter());
                    //设置默认显示第几组
                    stellarMap.setGroup(0, true);//是否 播放动画
                    //设置x和y方向显示的密度,一般x和y传入的是每组的数量就行了
                    stellarMap.setRegularity(11, 11);
                }
            });
        }
        return list;
    }

    class StellarMapAdapter implements StellarMap.Adapter {
        /**
         * 返回有几组数据，就是几页数据
         */
        @Override
        public int getGroupCount() {
            return list.size() / getCount(0);
        }

        /**
         * 返回每组有多少个数据,每组都有11个
         */
        @Override
        public int getCount(int group) {
            return 11;
        }

        /**
         * 返回需要随机摆放的View
         * group: 表示当前是第几组
         * position： 表示当前组中的位置
         */
        @Override
        public View getView(int group, int position, View convertView) {
            final TextView textView = new TextView(getActivity());
            //1.设置文本数据
            int listPosition = group * getCount(group) + position;
            textView.setText(list.get(listPosition));
            //2.设置随机的文字大小
            Random random = new Random();
            textView.setTextSize(random.nextInt(10) + 14);//14-23
            //3.设置随机的字体颜色
            int red = random.nextInt(150);//0-190
            int green = random.nextInt(150);//0-190
            int blue = random.nextInt(150);//0-190
            int color = Color.rgb(red, green, blue);//使用rgb混合生成一种新的颜色
            textView.setTextColor(color);
            //4.设置点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showToast(textView.getText().toString());
                }
            });

            return textView;
        }

        /**
         * 当执行完平移动画后下一组加载哪一组的数据，但是在源码中没有任何地方用到改方法，
         * 所以此方法并没有什么用
         */
        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        /**
         * 当执行完缩放动画后下一组加载哪一组的数据
         * group： 表示当前是第几组
         */
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            //0->1->2->0
            return (group + 1) % getGroupCount();
        }

    }
}
