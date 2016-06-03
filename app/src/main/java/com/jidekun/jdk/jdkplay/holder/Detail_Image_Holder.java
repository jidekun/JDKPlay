package com.jidekun.jdk.jdkplay.holder;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.activity.ImageActivity;
import com.jidekun.jdk.jdkplay.bean.AppInfo;
import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.global.ImageLoaderOptions;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;
import com.jidekun.jdk.jdkplay.utils.CommonUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by JDK on 2016/6/3.
 */
public class Detail_Image_Holder extends BaseHolder<AppInfo> {
    private LinearLayout ll;
    private Activity activity;
    private ArrayList<String> screen;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void bindData(AppInfo appInfo) {
        int width = CommonUtils.getDimens(R.dimen.dp90);
        int height = CommonUtils.getDimens(R.dimen.dp150);
        int margin = CommonUtils.getDimens(R.dimen.dp8);
        screen = appInfo.getScreen();
        for (int i = 0; i < screen.size(); i++) {
            ImageView iv = new ImageView(JDKContext.context);
            //设置图片的宽高
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            //设置左边距离,第一张图片不用设置
            params.leftMargin = (i == 0 ? 0 : margin);
            //将宽高和左边距设置给imageview
            iv.setLayoutParams(params);
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + screen.get(i), iv, ImageLoaderOptions.fadein_options);
            ll.addView(iv);
            //设置每一张图片的点击事件
            //内部类需要外部类的变量,需要加final
            final int item = i;
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, ImageActivity.class);
                    intent.putStringArrayListExtra("screen", screen);
                    intent.putExtra("screenItem", item
                    );

                    activity.startActivity(intent);
                }
            });
        }

    }

    @Override
    public View initHolderView() {
        View view = View.inflate(JDKContext.context, R.layout.detail_image, null);
        ll = (LinearLayout) view.findViewById(R.id.ll_screen);
        return view;
    }

}
