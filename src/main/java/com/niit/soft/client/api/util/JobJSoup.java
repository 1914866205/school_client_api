package com.niit.soft.client.api.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.soft.client.api.domain.model.Job;
import com.niit.soft.client.api.domain.model.JobType;
import com.niit.soft.client.api.mapper.JobTypeMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author su
 * @className JobTypeJSoup
 * @Description TODO
 * @Date 2020/6/11
 * @Version 1.0
 **/
@Component
public class JobJSoup {

    @Resource
    private JobTypeMapper jobTypeMapper;

    private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());


    public List<JobType> getData() {
        Set<String> set = new HashSet<>();
        List<JobType> list = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            //获取地址
            String url = "http://zp.58.com/hao123neiye/joblist?cateid=9224&size=30&localid=172";
            System.out.println(url);
            //创建CloseableHttpClient对象
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            //创建get对象
            HttpGet get = new HttpGet(url);
            //创建context对象
            HttpClientContext clientContext = HttpClientContext.create();
            //创建response对象
            CloseableHttpResponse response = null;
            try {
                //得到响应体
                response = closeableHttpClient.execute(get, clientContext);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //如果请求成功，则获取网页源码
            Integer SUCCESS = 200;
            if (response.getStatusLine().getStatusCode() == SUCCESS) {
                //获取响应对象实体，转化成UTF-8的字符串
                HttpEntity entity = response.getEntity();
                String res = null;
                try {
                    res = EntityUtils.toString(entity, "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //把res装换成JSONObject类型
                JSONObject jsonObject = JSONObject.parseObject(res);
                //获取date数据
                JSONArray list1 = jsonObject.getJSONArray("dataList");
                System.out.println(list1.size());
//                list1.forEach(System.out::println);
                list1.forEach(o -> {
                    //把遍历出来的数据转换JSONObject类型
                    JSONObject jsonObject1 = JSONObject.parseObject(o.toString());
                    String typeName = jobTypeMapper.selectName(jsonObject1.getString("entTrade"));
                    if ("".equals(jsonObject1.getString("entTrade")) || jsonObject1.getString("entTrade") == null || typeName != null) {
                        System.out.println("name为空或者已存在");
                    } else {
                        set.add(jsonObject1.getString("entTrade"));
                    }
                });
                for (String name : set) {
                    JobType jobType = JobType.builder()
                            .name(name)
                            .parentId(0L)
                            .isDeleted(false)
                            .gmtCreate(timestamp)
                            .gmtModified(timestamp)
                            .build();
                    list.add(jobType);
                }
            } else {
                System.out.println("请求失败");
            }
        }
        return list;
    }

    /**
     * 获取数据列表
     *
     * @return
     */
    public JSONArray getList() {
//        JSONArray list = null;
//        for (int i = 1; i < 2; i++) {
//            //获取地址
//            String url = "http://zp.58.com/hao123neiye/joblist?cateid=9224&size=30&localid=172";
//            System.out.println(url);
//            //创建CloseableHttpClient对象
//            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
//            //创建get对象
//            HttpGet get = new HttpGet(url);
//            //创建context对象
//            HttpClientContext clientContext = HttpClientContext.create();
//            //创建response对象
//            CloseableHttpResponse response = null;
//            try {
//                //得到响应体
//                response = closeableHttpClient.execute(get, clientContext);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            //如果请求成功，则获取网页源码
//            Integer SUCCESS = 200;
//            if (response.getStatusLine().getStatusCode() == SUCCESS) {
//                //获取响应对象实体，转化成UTF-8的字符串
//                HttpEntity entity = response.getEntity();
//                String res = null;
//                try {
//                    res = EntityUtils.toString(entity, "UTF-8");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                //把res装换成JSONObject类型
//                JSONObject jsonObject = JSONObject.parseObject(res);
//                //获取date数据
//                list = jsonObject.getJSONArray("dataList");
//                System.out.println(list.size());
////                list1.forEach(System.out::println);
//            } else {
//                System.out.println("请求失败");
//            }
//
//        }
//        return list;
        return null;
    }


    public Long getTypeId(String name) {
        QueryWrapper<JobType> wrapper = new QueryWrapper<>();
        wrapper.select("pk_job_type_id").eq("name", name);
        return jobTypeMapper.selectOne(wrapper).getPkJobTypeId();
    }


}
