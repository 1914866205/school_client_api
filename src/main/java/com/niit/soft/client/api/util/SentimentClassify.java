package com.niit.soft.client.api.util;

import com.baidu.aip.nlp.AipNlp;

import java.util.HashMap;

/**
 * @ClassName sentimentClassify
 * @Description 百度的情感分析
 * @Author xiaobinggan
 * @Date 2020/6/22 8:47 上午
 * @Version 1.0
 **/
public class SentimentClassify {
    //设置APPID/AK/SK
    private static final String APP_ID = "20501151";
    private static final String API_KEY = "MKMYRWIUsEsZETRyon0qRQ66";
    private static final String SECRET_KEY = "PrgHGo0m3wQUYpd1r11mwRWYIWK8yULX";

    public static String sentimentStatus(String text) {
        // 初始化一个AipNlp
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();

        // 情感倾向分析
        return client.sentimentClassify(text, options).toString();
    }

}
