package com.niit.soft.client.api.errends.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author wl
 * @ClassNametransaction
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transaction")
@EntityListeners(AuditingEntityListener.class)
public class Transaction {
    @Id
    @Column(name = "id" ,length = 30)
    private String id;
    /**
     * 订单id
     */
    @Column(name = "order_id", nullable = false)

    private String orderId;
    /**
     * 交易创建时间+
     */
    @Column(name = "transaction_create", nullable = false)
    private Timestamp transactionCreate;
    /**
     * 跑腿人
     */
    @Column(name = "errands_id", nullable = false)
    private String errandsId;
    /**
     * 完成时间  根据完成后更改
     */
    @Column(name = "transaction_end", nullable = false)
    private Timestamp transactionEnd;
    /**
     * 状态 0是抢单  1是取货并且送货  3是完成
     */
    @Column(name = "status", nullable = false)
    private Integer status;
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
