package com.niit.soft.client.api.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @ClassName HttpContextUtils
 * @Description TODO
 * @Author xiaobinggan
 * @Date 2020/6/11 8:27 上午
 * @Version 1.0
 **/
public class HttpContextUtils {
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HashMap<String, String> parseQueryString(String queryString) {
        HashMap<String, String> maps = new HashMap<String, String>();

        if (queryString == null || queryString == "") {
            return maps;
        }

        String[] kvs = queryString.split("&");
        String[] kvItem;
        for (String item : kvs) {
            kvItem = item.split("=");
            if (kvItem != null && kvItem.length == 2) {
                maps.put(kvItem[0], kvItem[1]);
            }
        }

        return maps;
    }

}