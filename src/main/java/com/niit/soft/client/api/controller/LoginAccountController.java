package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.annotation.ControllerWebLog;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.LoginAccountIncreased;
import com.niit.soft.client.api.domain.dto.SingleFieldDto;
import com.niit.soft.client.api.domain.model.LoginAccount;
import com.niit.soft.client.api.service.LoginAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName LoginAccountController.java
 * @Description TODO
 * @createTime 2020年06月17日 22:45:00
 */
@Slf4j
@RestController
@Api(value = "LoginAccountController", tags = {"第三方账号管理"})
public class LoginAccountController {
    @Resource
    private LoginAccountService loginAccountService;

    @ControllerWebLog(name = "increasedLoginAccount", isSaved = true)
    @ApiOperation(value = "绑定本地账号与第三方账号", notes = "添加第三方账号关联")
    @PostMapping("/LoginAccount/increased")
    ResponseResult  increasedLoginAccount(@RequestBody LoginAccountIncreased loginAccountIncreased) {
        log.info("-----/LoginAccount/increased被访问----**1**");
        return loginAccountService.increasedLoginAccount(loginAccountIncreased);
    }
    @ControllerWebLog(name = "findByJobNumber", isSaved = true)
    @ApiOperation(value = "根据学号查询是否关联第三方用户", notes = "参数为 field，值为学号，，存在则值为ok，不存在为no")
    @PostMapping("/LoginAccount/findByJobNumber")
    ResponseResult  findByJobNumber(@RequestBody SingleFieldDto singleFieldDto) {
        log.info("-----/LoginAccount/findByJobNumber被访问----**1**");
        log.info(singleFieldDto.toString());
        return loginAccountService.findByJobNumber(String.valueOf(singleFieldDto.getField()));
    }

    @ControllerWebLog(name = "deletedLoginAccount", isSaved = true)
    @ApiOperation(value = "根据学号查询删除关联第三方用户", notes = "参数为 field ， 值为学号")
    @PostMapping("/LoginAccount/deleted")
    ResponseResult  deletedLoginAccount(@RequestBody SingleFieldDto singleFieldDto) {
        return loginAccountService.deletedByJobNumber(String.valueOf(singleFieldDto.getField()));
    }
}
