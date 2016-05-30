package com.jidekun.jdk.jdkplay.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by JDK on 2016/5/27.
 * fragment 的工厂类
 */
public class FragmentFactory {
    public static Fragment create(int posision) {
        Fragment fragment =null;
        switch (posision) {
            case 0:
                fragment =new HomeF();
                break;
            case 1:
                fragment =new AppF();
                break;
            case 2:
                fragment =new GameF();
                break;
            case 3:
                fragment =new SubjectF();
                break;
            case 4:
                fragment =new RecommendF();
                break;
            case 5:
                fragment =new CatgoryF();
                break;
            case 6:
                fragment =new HotF();
                break;
        }
        return fragment;
    }

}
