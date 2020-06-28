package com.niit.soft.client.api.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName QQHttpClient.java
 * @Description TODO
 * @createTime 2020年06月17日 14:54:00
 * QQ工具类 主要用来解析QQ返回的信息
 */
public class QQHttpClient {
    //    QQ互联中提供的 appid 和 appkey
    public static final String APPID = "101883898";
    public static final String APPKEY = "6e4f3dccaab0a6105160003d7c08bc68";

    public static JSONObject parseJSONP(String jsonp) {
        int startIndex = jsonp.indexOf("(");
        int endIndex = jsonp.lastIndexOf(")");
        String json = jsonp.substring(startIndex + 1, endIndex);
        return JSONObject.parseObject(json);
    }

    //QQ返回信息
    public static String getAccessToken(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        String token = null;
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            if (result.indexOf("access_token") >= 0) {
                String[] array = result.split("&");
                for (String str : array) {
                    if (str.indexOf("access_token") >= 0) {
                        token = str.substring(str.indexOf("=") + 1);
                        break;
                    }
                }
            }
        }
        httpGet.releaseConnection();
        return token;
    }

    //qq返回信息
    public static String getOpenID(String url) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = parseJSONP(result);
        }

        httpGet.releaseConnection();

        if (jsonObject != null) {
            return jsonObject.getString("openid");
        } else {
            return null;
        }
    }

    //    CloseableHttpClient 默认会创建一个大小为5的连接池（针对RPC调用不频繁的情况），端到端的链接可以复用，配置evict相关的两个方法，一方面用于处理类似CLOSE_WAIT状态的异常链接，一方面用于处理IDLE状态的链接，其内部源码会开启一个定时任务去检测。
    public static JSONObject getUserInfo(String url) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSONObject.parseObject(result);
        }
        httpGet.releaseConnection();
        return jsonObject;
    }


}
