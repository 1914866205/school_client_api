package com.niit.soft.client.api.service;

import com.alipay.api.AlipayApiException;

/**
 * @ClassName AliPayService
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/1
 **/
public interface AliPayService {

    String AliPay( Double amount,
             String jobNumber,
             String rechargeType,
             Integer regexId) throws AlipayApiException;

}