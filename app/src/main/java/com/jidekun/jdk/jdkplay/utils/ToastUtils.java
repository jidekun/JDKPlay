package com.jidekun.jdk.jdkplay.utils;

import android.widget.Toast;

import com.jidekun.jdk.jdkplay.global.JDKContext;

/**
 * Created by JDK on 2016/5/28.
 */
public class ToastUtils {
    private static Toast toast;
    /**
     * 强大的吐司，能够连续弹的吐司
     * @param text
     */
    public static void showToast(String text){
        if(toast==null){
            toast = Toast.makeText(JDKContext.context, text,Toast.LENGTH_SHORT);
        }else {
            toast.setText(text);//如果不为空，则直接改变当前toast的文本
        }
        toast.show();
    }
}
