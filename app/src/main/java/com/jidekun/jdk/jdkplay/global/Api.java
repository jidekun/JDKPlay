package com.jidekun.jdk.jdkplay.global;

/**
 * Created by JDK on 2016/5/29.
 */
public class Api {
    //服务器的主机地址
    public static String SERVER_HOST = "http://127.0.0.1:8090/";
    //图片url的前缀 : http://127.0.0.1:8090/image?name=
    public static String IMAGE_PREFIX = SERVER_HOST +"image?name=";
    //home页的url地址
    public static String HOME = SERVER_HOST + "home?index=";
}
