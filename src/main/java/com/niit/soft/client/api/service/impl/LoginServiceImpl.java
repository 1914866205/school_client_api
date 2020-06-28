package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.dto.LoginDto;
import com.niit.soft.client.api.domain.dto.SmsPhoneDto;
import com.niit.soft.client.api.domain.dto.VerifyPhoneDto;
import com.niit.soft.client.api.domain.model.UserAccount;
import com.niit.soft.client.api.repository.UserAccountRepository;
import com.niit.soft.client.api.service.LoginService;
import com.niit.soft.client.api.service.SendSmsService;
import com.niit.soft.client.api.service.UserAccountService;
import com.niit.soft.client.api.util.JwtUtil;
import com.niit.soft.client.api.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/26 8:34
 * @Version 1.0
 **/
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserAccountRepository userAccountRepository;
    @Resource
    private UserAccountService userAccountService;
    @Resource
    private SendSmsService sendSmsService;

    @Override
    public String findIdByLoginDto(String userAccount, String password) {
        log.info(userAccount + "*****" + password);
        log.info("查询id{}", userAccountRepository.findIdByLoginDto(userAccount, Md5Util.getMd5(password, true, 32)));
        return userAccountRepository.findIdByLoginDto(userAccount, Md5Util.getMd5(password, true, 32));
    }

    @Override
    public ResponseResult login(LoginDto loginDto) throws UnsupportedEncodingException {
        //如果查到数据，返回用户数据
        String id = this.findIdByLoginDto(loginDto.getUserAccount(), loginDto.getPassword());

        if (id != null) {
            log.info("登录成功");
            log.info(userAccountService.findUserAccountById(String.valueOf(id)).toString());
            //创建返回的数据
            Map map = new HashedMap();
            map.put("UserAccount", userAccountService.findUserAccountById(id));
            map.put("token", JwtUtil.sign(loginDto.getUserAccount(), loginDto.getPassword()));
            log.info("生成的token{}", map.get("token"));
            return ResponseResult.success(map);
        }
        return ResponseResult.failure(ResultCode.USER_ACCOUNT_PASSWORD_ERROR);
    }

    @Override
    public ResponseResult qqLogin(LoginDto loginDto) throws UnsupportedEncodingException {
        //如果查到数据，返回用户数据
        String id = this.findIdByLoginDto(loginDto.getUserAccount(), loginDto.getPassword());

        if (id != null) {
            log.info("登录成功");
            log.info(userAccountService.findUserAccountById(id).toString());
            //创建返回的数据
            Map map = new HashedMap();
            map.put("UserAccount", userAccountService.findUserAccountById(id));
            map.put("token", JwtUtil.sign2(loginDto.getUserAccount(), loginDto.getPassword()));
            log.info("生成的token{}", map.get("token"));
            return ResponseResult.success(map);
        }
        return ResponseResult.failure(ResultCode.USER_ACCOUNT_PASSWORD_ERROR);
    }

    @Override
    public ResponseResult loginByPhone(VerifyPhoneDto verifyPhone) throws UnsupportedEncodingException {
        //如果查到数据，返回用户数据
        if (sendSmsService.verify(verifyPhone)) {
            log.info("登录成功");
            UserAccount userAccount = userAccountService.findUserAccountByInfo(verifyPhone.getPhoneNumber());
            log.info(userAccount.toString());
            Map map = new HashedMap();
            map.put("UserAccount", userAccount);
            map.put("token", JwtUtil.sign2(userAccount.getUserAccount(), userAccount.getPassword()));
            log.info("生成的token{}", map.get("token"));
            return ResponseResult.success(map);
        }
        return ResponseResult.failure(ResultCode.DATA_IS_WRONG);
    }

    @Override
    public ResponseResult flushUserAccount(SmsPhoneDto smsPhoneDto) {
        UserAccount userAccount = userAccountService.findUserAccountByInfo(smsPhoneDto.getPhoneNumber());
        log.info(userAccount.toString());
        Map map = new HashedMap();
        map.put("UserAccount", userAccount);
        return ResponseResult.success(map);
    }
}
