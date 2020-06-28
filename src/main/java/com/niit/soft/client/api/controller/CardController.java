package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.DepositDto;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.dto.SecondFieldDto;
import com.niit.soft.client.api.domain.dto.SingleFieldDto;
import com.niit.soft.client.api.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName CardController
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/26
 **/
@Slf4j
@RestController
@Api(tags = "一卡通接口")
public class CardController {
    @Resource
    private CardService service;

    /**
     * 查询所有消息
     *
     * @param pageDto
     * @return
     */
    @ApiOperation(value = "查询所有", notes = "请求参数为当前页和页面条数")
    @PostMapping("/card/all")
    ResponseResult findAllByPage(@RequestBody PageDto pageDto) {
        return service.findAllByPage(pageDto);
    }

    /**
     * 查询校园卡余额
     *
     * @param singleFieldDto
     * @return
     */
    @ApiOperation(value = "查询校园卡余额", notes = "请求参数为学号")
    @PostMapping("/card/balance")
    ResponseResult findCardBalanceByJobNumber(@RequestBody SingleFieldDto singleFieldDto) {
        return service.selectCardBalance(String.valueOf(singleFieldDto.getField()));
    }

    /**
     * 校园卡充值
     *
     * @param depositDto
     * @return
     */
    @ApiOperation(value = "校园卡充值", notes = "请求参数为校园卡号码，充值金额")
    @PostMapping("/card/deposit")
    ResponseResult insertCardBalance(@RequestBody DepositDto depositDto) {
        return service.insertCardBalance(depositDto.getCardNumber(), depositDto.getMoney());
    }

    /**
     * 电费充值
     *
     * @param secondFieldDto
     * @return
     */
    @ApiOperation(value = "电费充值", notes = "请求参数为房间id，充值金额")
    @PostMapping("/electricity/deposit")
    ResponseResult insertEleBalance(@RequestBody SecondFieldDto secondFieldDto) {
        return service.insertelectricityBalance(Long.parseLong(String.valueOf(secondFieldDto.getField1())), Double.valueOf(String.valueOf(secondFieldDto.getField2())));
    }

    /**
     * 网费充值
     *
     * @param depositDto
     * @return
     */
    @ApiOperation(value = "网费充值", notes = "请求参数为学号，充值金额")
    @PostMapping("/net/deposit")
    ResponseResult insertBalance(@RequestBody DepositDto depositDto) {
        return service.insertBalance(depositDto.getCardNumber(), depositDto.getMoney());
    }

    /**
     * 一卡通激活
     *
     * @param secondFieldDto
     * @return
     */
    @PostMapping("card/statusChange")
    @ApiOperation(value = "一卡通激活", notes = "请求参数为id，状态")
    ResponseResult updateStatus(@RequestBody SecondFieldDto secondFieldDto) {
        boolean status = Boolean.parseBoolean(secondFieldDto.getField2().toString());
        return service.updateStatus(Long.parseLong(String.valueOf(secondFieldDto.getField1())), status);
    }
}