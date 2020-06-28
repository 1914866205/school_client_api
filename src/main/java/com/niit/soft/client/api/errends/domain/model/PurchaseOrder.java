package com.niit.soft.client.api.errends.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author wl
 * @ClassNamepurchaseOrder
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {
    @Id
    @Column(name = "id" ,length = 30)

    private String id;
    /**
     * 目的地
     */
    @Column(name = "destination", nullable = false)
    private String destination;
    /**
     * 经度
     */
    @Column(name = "d_longitude", nullable = false)
    private String dLongitude;
    /**
     * 纬度
     */
    @Column(name = "d_dimension", nullable = false)
    private String dDimension;
    /**
     * 商品id
     */
    @Column(name = "commodity_id", nullable = false)
    private String commodityId;
    /**
     * 金额
     */
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    /**
     * 购买时间
     */
    @Column(name = "purchase_time", nullable = false)
    private Timestamp purchaseTime;
    /**
     * 是否取消
     */
    @Column(name = "is_cancle", nullable = false)
    private Integer isCancle;
    /**
     * 创建时间
     */
    //@JsonIgnore
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
