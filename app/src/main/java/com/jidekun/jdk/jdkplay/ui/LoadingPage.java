package com.jidekun.jdk.jdkplay.ui;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.jidekun.jdk.jdkplay.utils.CommonUtils;

/**
 * Created by JDK on 2016/5/28.
 */
public abstract class LoadingPage extends FrameLayout {
    public LoadingPage(Context context) {
        super(context);
        initLoadingPage();
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLoadingPage();
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLoadingPage();
    }

    //定义3种状态常量
    private static final int STATE_LOADING = 0; //加载中的状态
    private static final int STATE_ERROR = 1; //加载失败的状态
    private static final int STATE_SUCCESS = 2;//加载成功的状态
    //初始化状态不能定义为static ,不然会出现不显示加载页的情况,
    // 因为其一直在内存中保存这加载第一个页面是的状态,另外页面再加载时,会用到上一次页面的状态
    private    int PageState = STATE_LOADING;//表示界面当前的state，默认是加载中
    private View loadingView; //加载中
    private View errorView;
    private View successView;

    //将三个状态的view添加进来
    private void initLoadingPage() {
        //布局参数,填充
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (loadingView == null) {
            loadingView = View.inflate(JDKContext.context, R.layout.page_loading, null);
        }
        addView(loadingView, lp);
        if (errorView == null) {
            errorView = View.inflate(JDKContext.context, R.layout.page_error, null);
            Button bt = (Button) errorView.findViewById(R.id.btn_reload);
            bt.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    PageState = STATE_LOADING;
                    requestData();
                    showPager();
                }
            });
        }
        addView(errorView, lp);

        if (successView == null) {
            successView = createSuccessView();
        }
        //如果createSuccessView方法返回的对象为空
        if (successView == null) {
            throw new IllegalArgumentException("这个createSuccessView()方法不能返回空, The method createSuccessView() can not return null! ");
        } else {
            addView(successView, lp);
        }
        showPager();
        requestData();
    }

    //根据当前状态,切换该显示的view
    private void showPager() {
        //先将所有view隐藏
        loadingView.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.INVISIBLE);
        successView.setVisibility(View.INVISIBLE);
        //再判断哪个view显示
        switch (PageState) {
            case STATE_LOADING:
                loadingView.setVisibility(View.VISIBLE);
                break;
            case STATE_ERROR:
                errorView.setVisibility(View.VISIBLE);
                break;
            case STATE_SUCCESS:
                successView.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void requestData() {
        //向网络请求数据耗时操作,放在子线程
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                Object object = loadData();
                PageState = object == null ? STATE_ERROR : STATE_SUCCESS;
                //刷新界面属于更新ui,需要在主线程
                CommonUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        showPager();
                    }
                });
            }
        }.start();
    }

    //添加不同的布局让自己去实现
    public abstract View createSuccessView();

    //加载不同的数据,让子类自己去实现
    public abstract Object loadData();
}
