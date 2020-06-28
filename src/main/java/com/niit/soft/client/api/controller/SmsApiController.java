package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.annotation.ControllerWebLog;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.dto.SmsPhoneDto;
import com.niit.soft.client.api.domain.dto.VerifyPhoneDto;
import com.niit.soft.client.api.service.SendSmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description 短信服务控制器
 * @Author 涛涛
 * @Date 2020/5/21 10:31
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(value = "SmsApiController", tags = {"短信服务接口"})
public class SmsApiController {
    @Autowired
    private SendSmsService sendSmsService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "发送验证码", notes = "参数为 手机号")
    @PostMapping(value = "/sendCode")
    @ControllerWebLog(name = "code", isSaved = true)
    public ResponseResult code(@RequestBody SmsPhoneDto smsPhoneDto) {
        log.info("访问 /sendCode 接口");
        log.info("-----/sendCode-----请求参数：" + smsPhoneDto + "**1**");
        return sendSmsService.code(smsPhoneDto);
    }

    //校验验证码
    @ControllerWebLog(name = "verifyCode", isSaved = true)
    @ApiOperation(value = "校验验证码", tags = {"参数为手机号和接收的验证码"})
    @PostMapping(value = "/verifyCode")
    public ResponseResult verifyCode(@RequestBody VerifyPhoneDto verifyPhone) {
        log.info("-----/verifyCode-----请求参数：" + verifyPhone + "**1**");
        if (sendSmsService.verify(verifyPhone)) {
            log.info("验证码通过");
            return ResponseResult.success(true);
        } else {
            log.info("验证码失效或错误");
            return ResponseResult.failure(ResultCode.USER_VERIFY_CODE_ERROR);
        }
    }
}