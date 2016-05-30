package com.jidekun.jdk.jdkplay.http;



import com.jidekun.jdk.jdkplay.utils.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class HttpUtil {
    /**
     * 执行get请求，返回json字符串
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        String result = "";
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        LogUtil.e("HttpUtil", "请求的url:" + url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = httpResponse.getEntity();//获取响应体
                InputStream is = entity.getContent();//获取输入流
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];//1k的缓存区
                int len = -1;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                //关闭流和链接
                is.close();
                httpClient.getConnectionManager().closeExpiredConnections();//关闭闲置的链接，释放资源
                result = new String(baos.toByteArray(), "UTF-8");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
