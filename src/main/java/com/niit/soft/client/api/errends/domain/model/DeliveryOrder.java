package com.niit.soft.client.api.errends.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author wl
 * @ClassNamedeliveryOrder
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "delivery_order")
public class DeliveryOrder {
    @Id
    @Column(name = "id" ,length = 30)
    private String id;
    /**
     * 订单发起人
     */
    @Column(name = "founder_id", nullable = false)
    private String founderId;

    /**
     * 送件人姓名
     */
    @Column(name = "founder_name", nullable = false)
    private String founderName;
    /**
     * 送件人手机号
     */
    @Column(name = "founder_phonenumber", nullable = false)
    private String founderPhonenumber;

    /**
     * 初始位置
     */
    @Column(name = "origin_address", nullable = false)
    private String originAddress;
    /**
     * 出发地纬度
     */
    @Column(name = "o_longitude", nullable = false)
    private String oLongitude;
    /**
     * 出发地经度
     */
    @Column(name = "o_dimension", nullable = false)
    private String oDimension;
    /**
     * 目的地
     */
    @Column(name = "destination", nullable = false)
    private String destination;
    /**
     * 目的地经度
     */
    @Column(name = "d_longitude", nullable = false)
    private String dLongitude;
    /**
     * 目的地维度
     */
    @Column(name = "d_dimension", nullable = false)
    private String dDimension;
    /**
     * 商品详细信息
     */
    @Column(name = "commodity_id", nullable = false)
    private String commodityId;
    /**
     * 订单创建时间
     */
    @Column(name = "oder_create_time", nullable = false)
    private Timestamp oderCreateTime;
    /**
     * 送出时间（立即送出 或者指定时间）
     */
    @Column(name = "delivery_time", nullable = false)
    private Timestamp deliveryTime;
    /**
     * 0 发布 1是取消 2是正在进行   3是完成 4是已被抢单
     */
    @Column(name = "status", nullable = false)
    private Integer status;
    /**
     * 订单金额（起步价格6）
     */
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    /**
     * 收货人名字
     */
    @Column(name = "receiver_name", nullable = false)
    private String receiverName;
    /**
     * 收货人手机号
     */
    @Column(name = "receiver_phoneNumber", nullable = false)
    private String receiverPhoneNumber;
    /**
     * 备注
     */
    @Column(name = "remark", nullable = false)
    private String remark;
    /**
     * 创建时间
     */
    @JsonIgnore
    @Column(nullable = false)
    @CreatedDate
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @JsonIgnore
    @LastModifiedDate
    @Column(nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
//    @JsonIgnore
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;

}
