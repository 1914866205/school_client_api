package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @ClassName sys_order
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/25
 **/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysOrder {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pkOrderId;

    /**
     * 卡号
     */
    @Column(nullable = false, length = 32)
    private String jobNumber;

    /**
     * 类型
     */
    @Column(nullable = false, length = 16)
    private String orderType;

    /**
     * 金额
     */
    @Column(nullable = false)
    private Double orderMoney;

    /**
     * 订单号
     */
    @Column(nullable = false, length = 32)
    private String orderNumber;

    /**
     * 状态
     */
    @Column(nullable = false, length = 4)
    private Boolean status;

    /**
     * 缴费描述
     */
    @Column
    private String description;

    /**
     * 支付方式
     */
    @Column(nullable = false, length = 16)
    private String payMethod;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp gmtModified;

    /**
     * 删除标志
     */
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;
}