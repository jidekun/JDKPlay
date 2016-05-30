package com.jidekun.jdk.jdkplay.activity;


import android.os.Environment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;


import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.adapter.MainPagerAdapter;
import com.jidekun.jdk.jdkplay.http.HttpUtil;
import com.jidekun.jdk.jdkplay.ui.PagerSlidingTab;
import com.jidekun.jdk.jdkplay.utils.LogUtil;

import java.io.File;

public class MainActivity extends ActionBarActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ViewPager vp;
    private PagerSlidingTab pst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        setActionBar();

    }

    private void initView() {
        dl = (DrawerLayout) findViewById(R.id.dl_main);
        vp = (ViewPager) findViewById(R.id.vp);
        pst = (PagerSlidingTab) findViewById(R.id.pst);
        vp.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), this));
        //将viewpager指示器与viewpager关联 要实现getPageTitle方法
        pst.setViewPager(vp);
    }

    private void setActionBar() {
        //获得actionbar对象
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setIcon(R.drawable.ic_launcher);
        supportActionBar.setTitle("JDK电子市场");
        //设置按钮
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        //设置按钮可以被点击
        supportActionBar.setDisplayShowHomeEnabled(true);
        //替换home按钮
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, R.drawable.ic_drawer_am, 0, 0);
        //和actionbar同步和drawerlayout的状态
        actionBarDrawerToggle.syncState();
        //给图标增加动作 设置监听
//        dl.setDrawerListener(new DrawerLayout.DrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                actionBarDrawerToggle.onDrawerSlide(drawerView, slideOffset);
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                actionBarDrawerToggle.onDrawerOpened(drawerView);
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                actionBarDrawerToggle.onDrawerClosed(drawerView);
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//                actionBarDrawerToggle.onDrawerStateChanged(newState);
//            }
//        });
        //效果和上代码相同,因为actionBarDrawerToggle实现了DrawerListener接口
        dl.setDrawerListener(actionBarDrawerToggle);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //判断左边菜单的打开状态,如果是开 就关闭,否则打开
//                if (dl.isDrawerOpen(Gravity.LEFT)) {
//                    dl.closeDrawer(Gravity.LEFT);
//                } else {
//                    dl.openDrawer(Gravity.LEFT);
//                }
                //效果和上面代码相同
//                if (dl.isDrawerOpen(GravityCompat.START)) {
//                    dl.closeDrawer(GravityCompat.START);
//                } else {
//                    dl.openDrawer(GravityCompat.START);
//                }
                //替换上述判断 效果相同
                actionBarDrawerToggle.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

}
