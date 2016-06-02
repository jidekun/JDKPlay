package com.jidekun.jdk.jdkplay.fragment.base;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.adapter.BasicAdapter;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.jidekun.jdk.jdkplay.utils.ToastUtils;

import java.util.ArrayList;

/**
 * Created by JDK on 2016/5/31.
 */
public abstract class BaseListFragment<T> extends BaseFragment   implements ListView.OnItemClickListener {
    protected PullToRefreshListView refreshListView;
    protected ListView listView;
    protected BasicAdapter<T> basicAdapter;
    protected ArrayList<T> list = new ArrayList<T>();

    @Override
    public View createView() {
        refreshListView = (PullToRefreshListView) View.inflate(JDKContext.context, R.layout.ptr_listview, null);
        //设置下拉刷新的模式
        setPullToRefreshMode();
        //2.设置刷新监听器
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            /**
             * 下拉刷新和上拉加载更多都会执行该方法，
             */
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //直接调用父类请求数据的方法 也会调用本类的请求数据的方法 等价与调用本类的requstData方法
                loadingPage.requestData();
            }
        });
        //3.获取PullToRefreshListView内部包裹的ListView
        listView = refreshListView.getRefreshableView();
        listView.setOnItemClickListener(this);
        //设置listview的模式
        setListViewMode();
        //添加头布局
        addHeader();
        //获得适配器
        basicAdapter = getAdapter();
        listView.setAdapter(basicAdapter);
        return refreshListView;
    }

    /**
     * 获得具体的适配器让子类去实现
     *
     * @return
     */
    protected abstract BasicAdapter<T> getAdapter();

    /**
     * 设置下啦刷新的模式,如果子类需要其他类型的下啦模式,则实现此方法修改
     */
    protected void setPullToRefreshMode() {
        //设置既可以上拉也可以下拉
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    /**
     * 设置listview的模式 是否去掉分割线 是否将选择器变成透明,如果子类需要其他类型的下啦模式,则实现此方法修改
     */
    protected void setListViewMode() {
        //去掉分割线
        listView.setDividerHeight(0);
        //将listveiw的selector设置为透明
        listView.setSelector(android.R.color.transparent);
    }
    /**
     * 是否添加头布局,由子类自己决定
     */
    protected void addHeader() {
    }

    @Override
    public abstract Object requstData();

    /**
     * 检查如果是下拉刷新，则清空集合
     */
    public void checkPullFromStart() {
        //如果是下拉刷新状态,则是请求第一页的数据,先清空集合,再请求
        if (refreshListView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
            list.clear();
        }
    }
}
