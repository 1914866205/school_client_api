package com.niit.soft.client.api.domain.vo;

import org.springframework.stereotype.Component;

/**
 * @ClassName AlipayVo
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/1
 **/
@Component
public class AlipayVo {
    private String subject = "支付宝测试";//订单标题

    private static String out_trade_no;//商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复

    private String timout_express = "1h";//该笔订单允许的最晚付款时间

    private String totle_amout;//订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]

    private static String product_code = "FAST_INSTANT_TRADE_PAY";//销售产品码，商家和支付宝签约的产品码，为固定值 FAST_INSTANT_TRADE_PAY

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTimout_express() {
        return timout_express;
    }

    public void setTimout_express(String timout_express) {
        this.timout_express = timout_express;
    }

    public String getTotle_amout() {
        return totle_amout;
    }

    public void setTotle_amout(String totle_amout) {
        this.totle_amout = totle_amout;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }
}