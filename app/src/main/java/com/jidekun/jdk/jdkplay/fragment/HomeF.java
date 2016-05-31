package com.jidekun.jdk.jdkplay.fragment;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.adapter.BasicAdapter;
import com.jidekun.jdk.jdkplay.adapter.HomeAdapter;
import com.jidekun.jdk.jdkplay.adapter.HomeHeaderAdapter;
import com.jidekun.jdk.jdkplay.bean.AppInfo;
import com.jidekun.jdk.jdkplay.bean.Home;
import com.jidekun.jdk.jdkplay.fragment.base.BaseFragment;
import com.jidekun.jdk.jdkplay.fragment.base.BaseListFragment;
import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.jidekun.jdk.jdkplay.http.HttpUtil;
import com.jidekun.jdk.jdkplay.ui.LoadingPage;
import com.jidekun.jdk.jdkplay.utils.CommonUtils;

import java.util.ArrayList;


/**
 * Created by JDK on 2016/5/27.
 */
public class HomeF extends BaseListFragment<AppInfo> {
    //  private ArrayList<String> picture;//首页轮播图的url
    // private ArrayList<AppInfo> list = new ArrayList<>();
    // private PullToRefreshListView refreshListView;
    // private ListView listView;

   // private HomeAdapter homeAdapter;
    private ViewPager viewPager;


//    @Override
//    public View createView() {
////        refreshListView = (PullToRefreshListView) View.inflate(JDKContext.context, R.layout.ptr_listview, null);
//        //1.设置刷新模式
////        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);//设置既可以上拉也可以下拉
////        //2.设置刷新监听器
////        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
////            /**
////             * 下拉刷新和上拉加载更多都会执行该方法，
////             */
////            @Override
////            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
////                //直接调用父类请求数据的方法 也会调用本类的请求数据的方法 等价与调用本类的requstData方法
////                loadingPage.requestData();
////            }
////        });
//        //3.获取PullToRefreshListView内部包裹的ListView
////        listView = refreshListView.getRefreshableView();
////        listView.setDividerHeight(0);//去掉分割线
////        listView.setSelector(android.R.color.transparent);//将listveiw的selector设置为透明
//        //将ViewPager添加到listvew中,需要在setAdapter之前添加
//        //  addHeader();
//        //设置适配器
//        //   homeAdapter = new HomeAdapter(list);
//        //listView.setAdapter(homeAdapter);
//        //  return refreshListView;
//    }

    @Override
    protected BasicAdapter<AppInfo> getAdapter() {
        return new HomeAdapter(list);
    }

    /**
     * 添加头布局,ViewPager 轮播图
     */
    public void addHeader() {
        View headerView = View.inflate(getActivity(), R.layout.layout_home_header, null);
        viewPager = (ViewPager) headerView.findViewById(R.id.viewPager);
        listView.addHeaderView(headerView);
    }

//    /**
//     * 检查如果是下拉刷新，则清空集合
//     */
//    public void checkPullFromStart() {
//        //如果是下拉刷新状态,则是请求第一页的数据,先清空集合,再请求
//        if (refreshListView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
//            list.clear();
//        }
//    }

    /**
     * 向服务器请求数据
     */
    @Override
    public Object requstData() {
        checkPullFromStart();
        //返回json字符串 如果集合size为0 为第一次0到20的数据
        String s = HttpUtil.get(Api.HOME + list.size());
        //使用jsonutils,将json转换成对象
        //Home home = JsonUtil.parseJsonToBean(s, Home.class);
        Gson gson = new Gson();
        final Home home = gson.fromJson(s, Home.class);
        if (home != null) {
            list.addAll(home.getList());//更新数据
            CommonUtils.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    //只有第0也才有轮播图数据,
                    if (home.getPicture() != null && home.getPicture().size() > 0) {
                        viewPager.setAdapter(new HomeHeaderAdapter(home.getPicture()));
                        //设置默认选中比较大的一页
                        viewPager.setCurrentItem(home.getPicture().size() * 50);
                    }
                    basicAdapter.notifyDataSetChanged();//更新ui 主线程
                    //完成刷新,关闭刷新
                    refreshListView.onRefreshComplete();
                }
            });
        }
        return home;
    }


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            //让ViewPager选中下一页
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            //重新发消息
            handler.sendEmptyMessageDelayed(0, 2500);

        }
    };


    @Override
    public void onStart() {
        super.onStart();
        //发送空消息,让轮播图启动
        handler.sendEmptyMessageDelayed(0, 2500);
    }

    @Override
    public void onStop() {
        super.onStop();
        //移除消息
        handler.removeMessages(0);
    }
}
