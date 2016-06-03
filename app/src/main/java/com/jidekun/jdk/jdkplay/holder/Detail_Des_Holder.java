package com.jidekun.jdk.jdkplay.holder;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.bean.AppInfo;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;
import com.jidekun.jdk.jdkplay.utils.HeightAnim;
import com.jidekun.jdk.jdkplay.utils.LogUtil;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.view.ViewPropertyAnimator;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by JDK on 2016/6/3.
 */
public class Detail_Des_Holder extends BaseHolder<AppInfo> implements View.OnClickListener {
    @InjectView(R.id.tv_des)
    TextView tvDes;
    @InjectView(R.id.tv_author)
    TextView tvAuthor;
    @InjectView(R.id.iv_des_arrow)
    ImageView ivDesArrow;
    private int minHeight;
    private int maxHeight;

    @Override
    public void bindData(AppInfo appInfo) {
        //简介
        String des = appInfo.getDes();
        //坐着
        String author = appInfo.getAuthor();
        tvDes.setText(des);
        tvAuthor.setText(author);
        //设置最大行数为5
        tvDes.setMaxLines(5);
        //获得行数为5的时候 控件的高度
        tvDes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //移除监听
                tvDes.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获得五行的高度高度
                minHeight = tvDes.getHeight();
                //将行数设置为最大值以显示全部文字
                tvDes.setMaxLines(Integer.MAX_VALUE);
                tvDes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        //移除监听
                        tvDes.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        //获得显示全部文字时的高度
                        maxHeight = tvDes.getHeight();
                        //获得控件的布局参数
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tvDes.getLayoutParams();
                        //设置为5行的高度
                        layoutParams.height = minHeight;
                        tvDes.setLayoutParams(layoutParams);
                    }
                });
            }
        });
    }

    @Override
    public View initHolderView() {
        View view = View.inflate(JDKContext.context, R.layout.detail_jianjie, null);
        ButterKnife.inject(this, view);
        view.setOnClickListener(this);
        return view;
    }

    //是否在动画执行过程中
    private boolean isAnima;
    //是否事展开状态
    private boolean isExtend;

    @Override
    public void onClick(View v) {
        //如果是动画执行过程中 不进入下面代码
        if (isAnima) {
            return;
        }
        HeightAnim anim = null;
        if (isExtend) {
            anim = new HeightAnim(tvDes, maxHeight, minHeight);
        } else {
            anim = new HeightAnim(tvDes, minHeight, maxHeight);
        }
        anim.start(350);
        anim.setOnHeightChangeListener(new HeightAnim.OnHeightChangeListener() {
            @Override
            public void onHeightChange(int animatedValue) {
                //只需要在展开的时候显示全部的简介
                scrollView.scrollBy(0,minHeight-maxHeight);
            }
        });
        //翻转状态
        isExtend = !isExtend;
        //让箭头旋转
        ViewPropertyAnimator.animate(ivDesArrow)
                .rotationBy(180)
                .setDuration(350)
                .setListener(new MyLis())
                .start();
    }

    private ScrollView scrollView;

    public void setScrollView(ScrollView scrollView) {
        this.scrollView = scrollView;
    }


    class MyLis implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {
            isAnima = true;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            isAnima = false;
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }
}
