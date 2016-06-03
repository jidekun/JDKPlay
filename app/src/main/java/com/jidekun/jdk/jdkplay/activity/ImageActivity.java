package com.jidekun.jdk.jdkplay.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.adapter.BasePagerAdapter;
import com.jidekun.jdk.jdkplay.adapter.ImageAdapter;
import com.jidekun.jdk.jdkplay.utils.ToastUtils;

import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity {

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        vp = (ViewPager) findViewById(R.id.vp);
        Intent intent = getIntent();
        int screenItem = intent.getIntExtra("screenItem",0);
        ArrayList<String> screen = intent.getStringArrayListExtra("screen");
        ImageAdapter imageAdapter =new ImageAdapter(screen);
        vp.setCurrentItem(screenItem);
        vp.setAdapter(imageAdapter);
    }
}
