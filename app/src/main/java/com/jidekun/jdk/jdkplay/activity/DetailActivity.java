package com.jidekun.jdk.jdkplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.bean.AppInfo;
import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.holder.Detail_Down_Holder;
import com.jidekun.jdk.jdkplay.holder.Detail_Image_Holder;
import com.jidekun.jdk.jdkplay.holder.Detail_Info_Holder;
import com.jidekun.jdk.jdkplay.holder.Detail_Des_Holder;
import com.jidekun.jdk.jdkplay.holder.Detail_Safe_Holder;
import com.jidekun.jdk.jdkplay.http.HttpUtil;
import com.jidekun.jdk.jdkplay.ui.LoadingPage;
import com.jidekun.jdk.jdkplay.utils.CommonUtils;
import com.jidekun.jdk.jdkplay.utils.JsonUtil;

public class DetailActivity extends AppCompatActivity {
    //app info模块的控件

    private LoadingPage loadingPage;
    private AppInfo appInfo;
    private String packageName;
    private Detail_Info_Holder infoViewHolder;
    private Detail_Safe_Holder detail_safe_holder;
    private Detail_Image_Holder detail_image_holder;
    private Detail_Des_Holder detail_des_holder;
    private ScrollView sv;
    private Detail_Down_Holder detail_down_holder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        packageName = intent.getStringExtra("packageName");
        //设置loadingview 因为详情页也有三种状态
        loadingPage = new LoadingPage(this) {

            @Override
            public View createSuccessView() {
                View detail =  View.inflate(DetailActivity.this, R.layout.activity_detail, null);
                sv = (ScrollView) detail.findViewById(R.id.sv);
                LinearLayout ll = (LinearLayout) detail.findViewById(R.id.ll_detail);
                //上方详情页
                infoViewHolder = new Detail_Info_Holder();
                ll.addView(infoViewHolder.getHolderView());
                //安全信息页
                detail_safe_holder = new Detail_Safe_Holder();
                ll.addView(detail_safe_holder.getHolderView());
                //加载图片浏览
                detail_image_holder = new Detail_Image_Holder();
                ll.addView(detail_image_holder.getHolderView());
                //因为holder中需要开启activity 将上下文传过去,方便设置
                detail_image_holder.setActivity(DetailActivity.this);
                //加载下方简洁页
                detail_des_holder = new Detail_Des_Holder();
                ll.addView(detail_des_holder.getHolderView());
                detail_des_holder.setScrollView(sv);
                //下载分享栏
                detail_down_holder = new Detail_Down_Holder();
                ll.addView(detail_down_holder.getHolderView());
                return detail;
            }

            @Override
            public Object loadData() {
                //代码易读 抽取出另外一个方法
                return DetailActivity.this.loadData();
            }
        };
        setContentView(loadingPage);
    }

    private Object loadData() {
        String url = String.format(Api.Detail, packageName);
        String result = HttpUtil.get(url);
        appInfo = JsonUtil.parseJsonToBean(result, AppInfo.class);
        if (appInfo != null) {
            //更新UI
            CommonUtils.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    //调用更新UI
                    updateUI();
                }
            });
        }
        return appInfo;
    }

    /**
     * 更新Ui的方法
     */
    protected void updateUI() {
        //调用infoholder设置数据
        infoViewHolder.bindData(appInfo);
        detail_safe_holder.bindData(appInfo);
        detail_image_holder.bindData(appInfo);
        detail_des_holder.bindData(appInfo);
    }
}
