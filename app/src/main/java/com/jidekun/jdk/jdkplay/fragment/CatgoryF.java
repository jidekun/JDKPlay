package com.jidekun.jdk.jdkplay.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.adapter.CatgoryAdapter;
import com.jidekun.jdk.jdkplay.bean.Category;
import com.jidekun.jdk.jdkplay.bean.CategoryInfo;
import com.jidekun.jdk.jdkplay.fragment.base.BaseFragment;
import com.jidekun.jdk.jdkplay.global.Api;
import com.jidekun.jdk.jdkplay.global.JDKContext;
import com.jidekun.jdk.jdkplay.http.HttpUtil;
import com.jidekun.jdk.jdkplay.utils.CommonUtils;
import com.jidekun.jdk.jdkplay.utils.JsonUtil;
import com.jidekun.jdk.jdkplay.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JDK on 2016/5/27.
 */
public class CatgoryF extends BaseFragment {


    private ListView listView;

    @Override
    public View createView() {
        listView = (ListView) View.inflate(JDKContext.context, R.layout.category_listview, null);
        return listView;
    }

      ArrayList<Object> list = new ArrayList<>();

    @Override
    public Object requstData() {

        String result = HttpUtil.get(Api.Category);
        ArrayList<Category> category = (ArrayList<Category>) JsonUtil.parseJsonToList(result, new TypeToken<List<Category>>() {
        }.getType());
        if (list != null) {
            //将获得的String和CategoryInfo数据全存一个集合,在适配器中通过判断类型进行设置
            ArrayList<CategoryInfo> infos;
            for (Category temp : category) {
                //1.将title放入list中
                list.add(temp.getTitle());
                infos = temp.getInfos();
                //2.将infos中的CategoryInfo放入list中
                list.addAll(infos);
            }
            CommonUtils.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    listView.setAdapter(new CatgoryAdapter(list));
                }
            });
        }
        return list;
    }
}
