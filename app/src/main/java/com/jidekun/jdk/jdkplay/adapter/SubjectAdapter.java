package com.jidekun.jdk.jdkplay.adapter;

import com.jidekun.jdk.jdkplay.bean.Subject;
import com.jidekun.jdk.jdkplay.holder.SubjectViewHolder;
import com.jidekun.jdk.jdkplay.holder.base.BaseHolder;

import java.util.ArrayList;

/**
 * Created by JDK on 2016/5/31.
 */
public class SubjectAdapter extends BasicAdapter<Subject>{
    public SubjectAdapter(ArrayList<Subject> list) {
        super(list);
    }

    @Override
    public BaseHolder<Subject> getHolder(int position) {

        return new SubjectViewHolder();
    }
}
