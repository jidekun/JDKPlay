package com.jidekun.jdk.jdkplay.fragment.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jidekun.jdk.jdkplay.R;
import com.jidekun.jdk.jdkplay.ui.LoadingPage;
import com.jidekun.jdk.jdkplay.utils.CommonUtils;
import com.jidekun.jdk.jdkplay.utils.LogUtil;

/**
 * Created by JDK on 2016/5/28.
 */
public abstract class BaseFragment extends Fragment {

    public LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (loadingPage == null) {
            loadingPage = new LoadingPage(getActivity()) {
                @Override
                public View createSuccessView() {
                    return createView();
                }

                @Override
                public Object loadData() {
                    return requstData();
                }
            };
        } else {
            //需要拿loadingPage的父View（NoSaveStateFramelayout）去移除它自己
            //  LogUtil.e(this, "loadingPage已经不为空了: parent: " + loadingPage.getParent().getClass().getSimpleName());
            // CommonUtils.removeSelfFromParent(loadingPage);
            //但是呢，在Android5.0之后的FragmentManager已经不会在Fragment的view外边包裹一层，这意味着不用移除也不会报错;
        }

        return loadingPage;
    }


    public abstract View createView();

    public abstract Object requstData();
}
