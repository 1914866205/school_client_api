package com.niit.soft.client.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @ClassName PythonUtil
 * @Description 调用python工具类
 * @Author xiaobinggan
 * @Date 2020/6/19 8:25 下午
 * @Version 1.0
 **/
public class PythonUtil {

    public static Double getSentiment(String word) {
        Map<String, String> map = new HashMap<>();
        map.put("word", word);
        JSONObject json = new JSONObject();
        String[] argss = null;
        try {
            //编码第一遍
            json.put("word",
                    Base64.encodeBase64String(map.get("word").getBytes("utf-8")));
            //再次变成string
            String jsonStr = json.toString();
            jsonStr = Base64.encodeBase64String(jsonStr.getBytes("utf-8"));

            argss = new String[]{"python", "/Users/xiaobinggan/client_api/src/main/resources/python/chinese.py"};
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String str = null;
        StringBuilder a = new StringBuilder();
        Process process = null;// 执行py文件
        try {
            process = Runtime.getRuntime().exec(argss);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));

            while ((str = bfr.readLine()) != null) {
                System.out.println(str);
                a.append(str);
            }

            JSONObject jsonObject = JSON.parseObject(a.toString());
            System.out.println(jsonObject);


            bfr.close();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0.0;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//        String[] cmdarray = null;
//        String[] envp = null;
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("name", "名字");
//        map.put("number", "123456");
//        JSONObject json = new JSONObject();
//        json.put("name",
//                Base64.encodeBase64String(map.get("name").getBytes("utf-8")));
//        json.put("number",
//                Base64.encodeBase64String(map.get("number").getBytes("utf-8")));
//        String jsonStr = json.toString();
//        jsonStr = Base64.encodeBase64String(jsonStr.getBytes("utf-8"));
//        if (isWindows()) {
//            cmdarray = new String[]{"cmd", "/c", "python /Users/xiaobinggan/client_api/src/main/resources/python/test.py", jsonStr};
//            envp = new String[]{"path=D:\\Anaconda\\envs\\leantwo"};
//        } else {
//            System.out.println("在MAC系统下的");
//            cmdarray = new String[]{"python", "/Users/xiaobinggan/client_api/src/main/resources/python/test.py", jsonStr};
////            envp = new String[]{"export PATH=$PATH:/usr/local/bin/python"};
//        }
//        try {
//            Process process = Runtime.getRuntime().exec(cmdarray);
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(process.getInputStream()));
//            String line = null;
//            StringBuffer buffer = new StringBuffer();
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//                buffer.append(line);
//            }
//            in.close();
//            int re = process.waitFor();
//            System.out.println("有" + re + "程序");
//            System.out.println(buffer.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println(getSentiment("嘻嘻嘻"));
    }

    public static boolean isWindows() {
        return System.getProperties().getProperty("os.name")
                .toUpperCase().indexOf("WINDOWS") != -1;
    }

//    public static String unicodeToString(String str) {
//
//        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
//
//        Matcher matcher = pattern.matcher(str);
//
//        char ch;
//
//        while (matcher.find()) {
//
//            ch = (char) Integer.parseInt(matcher.group(2), 16);
//
//            str = str.replace(matcher.group(1), ch+"" );
//
//        }
//
//        return str;
//
//    }

}
