package com.niit.soft.client.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tao
 * @version 1.0
 * @ClassName StringUtil
 * @Description 字符串工具类
 * @date 2020-05-26 23:19
 **/
public class StringUtil {

    /**
     * 正则判断手机号码
     *
     * @param str
     * @return
     */
    public static boolean isMobile(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,6,9,5,7,8][0-9]{9}$");
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

}
