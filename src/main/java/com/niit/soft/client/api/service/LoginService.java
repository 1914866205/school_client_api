package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.LoginDto;
import com.niit.soft.client.api.domain.dto.SmsPhoneDto;
import com.niit.soft.client.api.domain.dto.VerifyPhoneDto;
import com.niit.soft.client.api.domain.model.SysUser;

import java.io.UnsupportedEncodingException;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/26 8:34
 * @Version 1.0
 **/
public interface LoginService {
    /**
     * 登录
     *
     * @param
     * @return 返回用户信息
     */
    String findIdByLoginDto(String userAccount, String password);


    ResponseResult login(LoginDto loginDto) throws UnsupportedEncodingException;
    ResponseResult qqLogin(LoginDto loginDto) throws UnsupportedEncodingException;

    /**
     * 手机验证码登录
     *
     * @param verifyPhone
     * @return
     */
    ResponseResult loginByPhone(VerifyPhoneDto verifyPhone) throws UnsupportedEncodingException;


    /**
     * 刷新用户信息
     *
     * @param smsPhoneDto
     * @return
     */
    ResponseResult flushUserAccount(SmsPhoneDto smsPhoneDto);

}
