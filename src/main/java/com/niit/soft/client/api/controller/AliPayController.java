package com.niit.soft.client.api.controller;

import com.alipay.api.AlipayApiException;
import com.niit.soft.client.api.annotation.ControllerWebLog;
import com.niit.soft.client.api.service.AliPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName AliPayController
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/1
 **/
@RestController
@Api(tags = "支付宝支付接口")
public class AliPayController {
    @Resource
    private AliPayService aliPayService;

    /**
     * 支付接口
     *
     * @param
     * @return
     * @throws AlipayApiException
     */
    @ApiOperation("支付接口")
    @GetMapping(value = "alipay/toPay")
    @ControllerWebLog(name = "AliPay", isSaved = true)
    public String AliPay(@RequestParam("amount") Double amount,
                        @RequestParam("jobNumber") String jobNumber,
                         @RequestParam("rechargeType") String rechargeType,
                        @RequestParam("regexId") Integer regexId) throws AlipayApiException {
        return aliPayService.AliPay(amount,jobNumber,rechargeType,regexId);
    }

    /**
     * 异步通知
     *
     * @return
     */
    @ApiOperation("支付异步通知接口")
    @GetMapping("alipay/notify_url")
    @ControllerWebLog(name = "notifyAliPay", isSaved = true)
    public String notifyAliPay() {
        return " a li pay notify ";
    }

    /**
     * 回调接口
     *
     * @return
     */
    @ApiOperation("支付完成以后的回调接口")
    @GetMapping("alipay/return_url")
    @ControllerWebLog(name = "returnAliPay", isSaved = true)
    public String returnAliPay() {
        return " a li pay return ";
    }
}