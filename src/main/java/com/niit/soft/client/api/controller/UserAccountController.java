package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.annotation.ControllerWebLog;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.QueryDto;
import com.niit.soft.client.api.domain.dto.SingleFieldDto;
import com.niit.soft.client.api.domain.dto.UpdateUserAccountDto;
import com.niit.soft.client.api.domain.model.SysFeedback;
import com.niit.soft.client.api.domain.model.UserAccount;
import com.niit.soft.client.api.service.SysFeedbackService;
import com.niit.soft.client.api.service.UserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysUserAccountController
 * @Description 用户控制器
 * @date 2020-05-26 11:08
 **/
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "UserController", tags = {"用户模块接口"})
public class UserAccountController {
    @Resource
    private UserAccountService userAccountService;

    @Resource
    private SysFeedbackService sysFeedbackService;

    /**
     * 修改个人资料
     *
     * @param updateUserAccountDto
     * @return
     */
    @ApiOperation(value = "修改个人信息数据", notes = "请求参数为用户id、头像、昵称、性别、地址")
    @PostMapping("/update/info")
    @ControllerWebLog(name = "updateInfo", isSaved = true)
    ResponseResult updateInfo(@RequestBody UpdateUserAccountDto updateUserAccountDto) {
        log.info("-----/update/info-----请求参数：" + updateUserAccountDto + "**1**");
        return userAccountService.updateUserInfo(updateUserAccountDto);
    }

    /**
     * 修改手机号码
     *
     * @param userAccount
     * @return
     */
    @ApiOperation(value = "修改手机号码", notes = "请求参数为手机号码")
    @PostMapping("/update/phone")
    ResponseResult updatePhoneNumber(@RequestBody UserAccount userAccount) {
        return userAccountService.updateUserPhoneNumber(userAccount);
    }

    /**
     * 匿名反馈
     */
    @ApiOperation(value = "匿名反馈接口", notes = "请求参数为主题、内容、联系方式（选填）")
    @PostMapping(value = "/feedback")
    @ControllerWebLog(name = "insertSysFeedback", isSaved = true)
    ResponseResult insertSysFeedback(@RequestBody SysFeedback sysFeedback) {
        log.info("-----/feedback-----请求参数：" + sysFeedback + "**1**");
        return sysFeedbackService.insertSysFeedback(sysFeedback);
    }

    @ApiOperation(value = "根据用户id查询用户信息", notes = "请求参数为用户id(必填)")
    @PostMapping(value = "/single/id")
    @ControllerWebLog(name = "getUserAccountInfoById", isSaved = true)
    public UserAccount getUserAccountInfoById(@RequestBody SingleFieldDto singleFieldDto) {
        log.info("-----/single/id-----请求参数：" + singleFieldDto.getField().toString() + "**1**");
        return userAccountService.findUserAccountById(singleFieldDto.getField().toString());
    }

    @ApiOperation(value = "模糊查询用户接口", notes = "请求参数为keywords关键字")
    @PostMapping("/fuzzyQuery")
    ResponseResult findUserAccountLike(@RequestBody QueryDto queryDto) {
        return userAccountService.findUserAccountLike(queryDto.getKeywords());
    }

}
