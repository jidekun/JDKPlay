package com.jidekun.jdk.jdkplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.bean.AppInfo;
import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.global.ImageLoaderOptions;
import com.jidekun.jdk.jdkplay.http.HttpUtil;
import com.jidekun.jdk.jdkplay.ui.LoadingPage;
import com.jidekun.jdk.jdkplay.utils.CommonUtils;
import com.jidekun.jdk.jdkplay.utils.JsonUtil;
import com.jidekun.jdk.jdkplay.utils.ToastUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DetailActivity extends AppCompatActivity {
    //app info模块的控件
    private ImageView iv_icon;
    private RatingBar rb_star;
    private TextView tv_name, tv_download_num, tv_version, tv_date, tv_size;
    private LoadingPage loadingPage;
    private AppInfo appInfo;
    private String packageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        packageName = intent.getStringExtra("packageName");
        //设置loadingview 因为详情页也有三种状态
        loadingPage = new LoadingPage(this) {
            @Override
            public View createSuccessView() {
                //加载一个成功的局部
                View view = View.inflate(DetailActivity.this, R.layout.activity_detail, null);
                iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
                rb_star = (RatingBar) view.findViewById(R.id.rb_star);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                tv_download_num = (TextView) view.findViewById(R.id.tv_download_num);
                tv_version = (TextView) view.findViewById(R.id.tv_version);
                tv_date = (TextView) view.findViewById(R.id.tv_date);
                tv_size = (TextView) view.findViewById(R.id.tv_size);
                return view;
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
        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + appInfo.getIconUrl(), iv_icon, ImageLoaderOptions.round_options);
        tv_name.setText(appInfo.getName());
        rb_star.setRating(appInfo.getStars());
        tv_download_num.setText("下载：" + appInfo.getDownloadNum());
        tv_version.setText("版本：" + appInfo.getVersion());
        tv_date.setText("日期：" + appInfo.getDate());
        tv_size.setText("大小：" + Formatter.formatFileSize(this, appInfo.getSize()));
    }
}
