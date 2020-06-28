package com.niit.soft.client.api.errends.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.niit.soft.client.api.errends.domain.model.Commodity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author wl
 * @ClassNameDeliveryOderInformationVo
 * @Description 订单详情
 * @Date 2020/6/10
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryOderInformationVo {
    /**
     * 订单id
     */
    private String id;
    /**
     * 订单发起人
     */
    private String founderId;
    /**
     * 订单发起人姓名
     */
    private String founderName;
    /**
     * 订单发起人手机号
     */
    private  String founderPhonenumber;
    /**
     * 初始位置
     */
    private String originAddress;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 商品详细信息
     */
    private Commodity commodity;
    /**
     * 订单创建时间
     */
    private Timestamp oderCreateTime;
    /**
     * 送出时间（立即送出 或者指定时间）
     */
    private Timestamp deliveryTime;
    /**
     * 订单取消时间
     */
    private Timestamp cancleTime;
    /**
     * 完成模块
     */
    //完成人名字
    private String name;
    /**
     * 完成人的手机号
     */
    private String errendsPhoneNumber;
    /**
     * 完成时间
     */

    private Timestamp finshTime;
    /**
     * 订单金额（起步价格6）
     */
    private BigDecimal amount;
    /**
     * 收货人名字
     */
    private String receiverName;
    /**
     * 收货人手机号
     */
    private String receiverPhoneNumber;
    /**
     * 备注
     */
    private String remark;

}
