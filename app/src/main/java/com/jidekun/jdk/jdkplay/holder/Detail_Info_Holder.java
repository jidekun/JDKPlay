package com.jidekun.jdk.jdkplay.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.bean.AppInfo;
import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.global.ImageLoaderOptions;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by JDK on 2016/6/3.
 */
public class Detail_Info_Holder extends BaseHolder<AppInfo> {

    private ImageView iv_icon;
    private RatingBar rb_star;
    private TextView tv_name, tv_download_num, tv_version, tv_date, tv_size;

    @Override
    public void bindData(AppInfo appInfo) {
        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + appInfo.getIconUrl(), iv_icon, ImageLoaderOptions.round_options);
        tv_name.setText(appInfo.getName());
        rb_star.setRating(appInfo.getStars());
        tv_download_num.setText("下载：" + appInfo.getDownloadNum());
        tv_version.setText("版本：" + appInfo.getVersion());
        tv_date.setText("日期：" + appInfo.getDate());
        tv_size.setText("大小：" + Formatter.formatFileSize(JDKContext.context, appInfo.getSize()));
    }

    @Override
    public View initHolderView() {
        //加载一个成功的局部
        View view = View.inflate(JDKContext.context, R.layout.detail_app_info, null);
        iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        rb_star = (RatingBar) view.findViewById(R.id.rb_star);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_download_num = (TextView) view.findViewById(R.id.tv_download_num);
        tv_version = (TextView) view.findViewById(R.id.tv_version);
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        tv_size = (TextView) view.findViewById(R.id.tv_size);
        return view;
    }
}
