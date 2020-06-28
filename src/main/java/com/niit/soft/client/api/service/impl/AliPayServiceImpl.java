package com.niit.soft.client.api.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.niit.soft.client.api.config.AliPayConfig;
import com.niit.soft.client.api.domain.model.SysOrder;
import com.niit.soft.client.api.domain.vo.AlipayVo;
import com.niit.soft.client.api.repository.OrderRepository;
import com.niit.soft.client.api.service.AliPayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AliPayServiceImpl
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/1
 **/
@Service
public class AliPayServiceImpl implements AliPayService {
    @Resource
    private AlipayVo staticVo;
    @Resource
    private OrderRepository orderRepository;

    @Override
    public String AliPay(Double amount,
                         String jobNumber,
                         String rechargeType,
                         Integer regexId) throws AlipayApiException {

        // 构建支付数据信息
        Map<String, String> data = new HashMap<>();
        data.put("subject", staticVo.getSubject()); //订单标题
        data.put("out_trade_no", new SimpleDateFormat().format(new Date())); //商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复      //此处模拟订单号为时间
        data.put("timeout_express", staticVo.getTimout_express()); //该笔订单允许的最晚付款时间
        data.put("total_amount", String.valueOf(amount)); //订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
        data.put("product_code", "FAST_INSTANT_TRADE_PAY"); //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY


        //构建客户端

        AlipayClient alipayClient = new DefaultAlipayClient(
                AliPayConfig.gatewayUrl,
                AliPayConfig.app_id,
                AliPayConfig.merchant_private_key,
                "json",
                AliPayConfig.charset,
                AliPayConfig.alipay_public_key,
                AliPayConfig.sign_type);
        if (regexId == 1) {
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();// APP支付
            request.setNotifyUrl(AliPayConfig.notify_url);
            request.setReturnUrl(AliPayConfig.return_url);
            request.setBizContent(JSON.toJSONString(data));
            SysOrder sysOrder =
                    SysOrder.builder().description(rechargeType).jobNumber(jobNumber).orderMoney(amount).orderNumber(
                            "10" + RandomUtil.randomInt(80000, 1000000)).isDeleted(false).gmtCreate(Timestamp.valueOf(LocalDateTime.now())).orderType("支出").payMethod("支付宝扫码").status(true).build();
            orderRepository.save(sysOrder);
            String response = alipayClient.pageExecute(request).getBody();
            return response;
        } else if (regexId== 2) {
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();// 网页支付
            request.setNotifyUrl(AliPayConfig.notify_url1);
            request.setReturnUrl(AliPayConfig.return_url1);
            request.setBizContent(JSON.toJSONString(data));
            SysOrder sysOrder =
                    SysOrder.builder().description(rechargeType).jobNumber(jobNumber).orderMoney(amount).orderNumber(
                            "10" + RandomUtil.randomInt(80000, 1000000)).isDeleted(false).gmtCreate(Timestamp.valueOf(LocalDateTime.now())).orderType("支出").payMethod("支付宝扫码").status(true).build();
            orderRepository.save(sysOrder);
            String response = alipayClient.pageExecute(request).getBody();
            return response;
        } else {
            AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();  //移动h5
            request.setNotifyUrl(AliPayConfig.notify_url2);
            request.setReturnUrl(AliPayConfig.return_url2);
            request.setBizContent(JSON.toJSONString(data));
            SysOrder sysOrder =
                    SysOrder.builder().description(rechargeType).jobNumber(jobNumber).orderMoney(amount).orderNumber(
                            "10" + RandomUtil.randomInt(80000, 1000000)).isDeleted(false).gmtCreate(Timestamp.valueOf(LocalDateTime.now())).orderType("支出").payMethod("支付宝扫码").status(true).build();
            orderRepository.save(sysOrder);
            String response = alipayClient.pageExecute(request).getBody();
            return response;
        }
    }
}
