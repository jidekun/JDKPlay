package com.jidekun.jdk.jdkplay.holder;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.bean.AppInfo;
import com.jidekun.jdk.jdkplay.bean.SafeInfo;
import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.global.ImageLoaderOptions;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;
import com.jidekun.jdk.jdkplay.utils.CommonUtils;
import com.jidekun.jdk.jdkplay.utils.PaddingTopAnim;
import com.jidekun.jdk.jdkplay.utils.ToastUtils;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by JDK on 2016/6/3.
 */
public class Detail_Safe_Holder extends BaseHolder<AppInfo> implements View.OnClickListener {
    @InjectView(R.id.iv_safe_image1)
    ImageView ivSafeImage1;
    @InjectView(R.id.iv_safe_image2)
    ImageView ivSafeImage2;
    @InjectView(R.id.iv_safe_image3)
    ImageView ivSafeImage3;
    @InjectView(R.id.iv_safe_arrow)
    ImageView ivSafeArrow;
    @InjectView(R.id.iv_safe_icon1)
    ImageView ivSafeIcon1;
    @InjectView(R.id.tv_safe_des1)
    TextView tvSafeDes1;
    @InjectView(R.id.iv_safe_icon2)
    ImageView ivSafeIcon2;
    @InjectView(R.id.tv_safe_des2)
    TextView tvSafeDes2;
    @InjectView(R.id.ll_safe2)
    LinearLayout llSafe2;
    @InjectView(R.id.iv_safe_icon3)
    ImageView ivSafeIcon3;
    @InjectView(R.id.tv_safe_des3)
    TextView tvSafeDes3;
    @InjectView(R.id.ll_safe3)
    LinearLayout llSafe3;
    @InjectView(R.id.ll_safe_container)
    LinearLayout llSafeContainer;
    private int maxPaddingTop;
    private int minPaddingTop;
    private View view;

    @Override
    public void bindData(AppInfo appInfo) {
        ArrayList<SafeInfo> safe = appInfo.getSafe();
        //一定会有一个数据
        SafeInfo safeInfo1 = safe.get(0);

        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + safeInfo1.getSafeUrl(), ivSafeImage1, ImageLoaderOptions.fadein_options);
        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + safeInfo1.getSafeDesUrl(), ivSafeIcon1, ImageLoaderOptions.fadein_options);
        tvSafeDes1.setText(safeInfo1.getSafeDes());

        if (safe.size() == 2) { //判断是否有第二 个数据
            SafeInfo safeInfo2 = safe.get(1);
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + safeInfo2.getSafeUrl(), ivSafeImage2, ImageLoaderOptions.fadein_options);
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + safeInfo2.getSafeDesUrl(), ivSafeIcon2, ImageLoaderOptions.fadein_options);
            tvSafeDes2.setText(safeInfo2.getSafeDes());
            //隐藏第三个
            llSafe3.setVisibility(View.GONE);
        } else if (safe.size() == 3) {//判断是否有第三个数据
            SafeInfo safeInfo2 = safe.get(1);
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + safeInfo2.getSafeUrl(), ivSafeImage2, ImageLoaderOptions.fadein_options);
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + safeInfo2.getSafeDesUrl(), ivSafeIcon2, ImageLoaderOptions.fadein_options);
            tvSafeDes2.setText(safeInfo2.getSafeDes());

            SafeInfo safeInfo3 = safe.get(2);
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + safeInfo3.getSafeUrl(), ivSafeImage3, ImageLoaderOptions.fadein_options);
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + safeInfo3.getSafeDesUrl(), ivSafeIcon3, ImageLoaderOptions.fadein_options);
            tvSafeDes3.setText(safeInfo3.getSafeDes());
        } else { //没有第二和第三个数据 将第二和第三隐藏
            llSafe2.setVisibility(View.GONE);
            llSafe3.setVisibility(View.GONE);
        }
        //获取正常状态下的padding值,为最大值
        maxPaddingTop = llSafeContainer.getPaddingTop();
        //添加是视图绘制是否完成的监听
        llSafeContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llSafeContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获得高度
                int measuredHeight = llSafeContainer.getMeasuredHeight();
                //控件的高度的赋值为将控件完成隐藏所设置的padding,获得视图绘制结束后的高度,此高度百分之百可以获得实际高度
                minPaddingTop = -1 * llSafeContainer.getMeasuredHeight();
                //默认状态下控件应该是隐藏的
                llSafeContainer.setPadding(llSafeContainer.getPaddingLeft(), minPaddingTop, llSafeContainer.getPaddingRight(), llSafeContainer.getPaddingBottom());
            }
        });
        //一开始让整个View移动到左边
        ViewHelper.setTranslationX(view, -1 * view.getMeasuredWidth());
        ViewPropertyAnimator.animate(view)
                .translationXBy(view.getMeasuredWidth())
                .setInterpolator(new OvershootInterpolator())
                .setDuration(400)
                .setStartDelay(600)
                .start();
    }


    @Override
    public View initHolderView() {
        view = View.inflate(JDKContext.context, R.layout.detail_safe, null);
        //设置整个view的点击事件
        view.setOnClickListener(this);
        ButterKnife.inject(this, view);
        return view;
    }

    //是否在动画执行状态
    private boolean isAnim;
    //是否是打开状态
    private boolean isExtend;

    @Override
    public void onClick(View v) {
        PaddingTopAnim pta = null;
        //如果正在执行动画,则不执行
        if (isAnim) {
            return;
        }
        if (isExtend) {
            pta = new PaddingTopAnim(llSafeContainer, maxPaddingTop, minPaddingTop);
        } else {
            pta = new PaddingTopAnim(llSafeContainer, minPaddingTop, maxPaddingTop);
        }

        pta.start(350);
        isExtend = !isExtend;
        //将箭头旋转
        ViewPropertyAnimator.animate(ivSafeArrow)
                .rotationBy(180)
                .setListener(new MyLis())
                .setDuration(350).start();
    }

    class MyLis implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {
            //动画开始时
            isAnim = true;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            //动画结束时
            isAnim = false;
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }
}
