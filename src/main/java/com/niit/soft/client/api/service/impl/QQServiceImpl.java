package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.model.LoginAccount;
import com.niit.soft.client.api.domain.model.UserAccount;
import com.niit.soft.client.api.exception.CustomException;
import com.niit.soft.client.api.repository.LoginAccountRepository;
import com.niit.soft.client.api.repository.UserAccountRepository;
import com.niit.soft.client.api.service.QQService;
import com.niit.soft.client.api.util.QQHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName QQServiceImpl.java
 * @Description QQ第三方登录
 * @createTime 2020年06月17日 21:24:00
 */
@Slf4j
@Service
public class QQServiceImpl implements QQService {

    @Value("${qq.oauth.http}")
    private String http;

    @Resource
    private LoginAccountRepository loginAccountRepository;
    @Resource
    private UserAccountRepository userAccountRepository;


    @Override
    public String qqRedirect(HttpSession session) {
        //QQ互联中的回调地址
        String backUrl = http + "/connect";
        //用于第三方应用防止CSRF攻击
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        session.setAttribute("state", uuid);
//        redirect uri is illegal(100010)
//        通知: QQ互联加强网站应用回调地址校验
//        回调地址此处应该改端口
//        http://www.ntt1914866205.xyz:8080
        //Step1：获取Authorization Code
        String url = "https://graph.qq.com/oauth2.0/authorize?response_type=code" +
                "&client_id=" + QQHttpClient.APPID +
                "&redirect_uri=" + URLEncoder.encode(backUrl) +
                "&state=" + uuid;
        log.info("--------重定向的地址-------");
        log.info(url);
        return url;
    }

    @Override
    public String connect(HttpServletRequest request) throws IOException {
//    public String connect(HttpServletRequest request) throws IOException {
        //此处本想用8080端口，但因为备案时用的是域名备案，无端口号，所以回调地址只能是 http://www.ntt1914866205.xyz/connect
        HttpSession session = request.getSession();
        //qq返回的信息
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String uuid = (String) session.getAttribute("state");
        log.info("code>>>>>>>>>>" + code);
        log.info("state>>>>>>>>>>" + code);
        log.info("uuid>>>>>>>>>>" + uuid);
        if (uuid != null) {
            if (!uuid.equals(state)) {
                throw new CustomException("QQ,state错误", ResultCode.DATA_IS_WRONG);
            }
        }

        //Step2：通过Authorization Code获取Access Token
        String backUrl = http + "/connect";
        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code" +
                "&client_id=" + QQHttpClient.APPID +
                "&client_secret=" + QQHttpClient.APPKEY +
                "&code=" + code +
                "&redirect_uri=" + backUrl;

        String access_token = QQHttpClient.getAccessToken(url);
        log.info(access_token);
        //Step3: 获取回调后的 openid 值
        url = "https://graph.qq.com/oauth2.0/me?access_token=" + access_token;
        log.info("获取回调后的 openid 值>>>>>>>>" + url);
        String openid = QQHttpClient.getOpenID(url);
        log.info("openid 值>>>>>>>>" + openid);
//        openid可以唯一标识一个用户
        //先在用户账号表里查，如果有，则返回数据
        //如果有数据，则在用户表里查到该用户
        boolean isExists = loginAccountRepository.existsLoginAccountByQqOpenIdEquals(openid);
        if (isExists) {
            LoginAccount loginAccount = loginAccountRepository.getLoginAccountByQqOpenIdEquals(openid);
            UserAccount userAccount = userAccountRepository.findUserAccountByInfo(loginAccount.getJobNumber());
            log.info(">>>>>>>>>>>>>>>>>>>>" + userAccount.toString());
            String redirectUrl = "http://localhost:8088/#/layout?jobNumber=" + userAccount.getJobNumber() + "&password="+userAccount.getPassword();
            log.info(redirectUrl);
            return redirectUrl;
        }
        String redirectUrl = "http://localhost:8088/#/base?openid=" + openid;
        log.info(redirectUrl);
        return redirectUrl;
    }
}

