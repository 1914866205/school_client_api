package com.niit.soft.client.api.errends.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author wl
 * @ClassNameCancleDeliveryOrder
 * @Description 取消跑腿记录表
 * @Date 2020/6/10
 * @Version 1.0
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cancle_delivery_order")
public class CancleDeliveryOrder {
    /**
     * id
     */
    @Id
    @Column(name = "id" ,length = 30)
    private String id;
    /**
     * 订单编号
     */
    @Column(name = "oder_id", nullable = false)
    private String oderId;
    /**
     * 取消时间
     */
    @Column(name = "cancle_time", nullable = false)
    private Timestamp cancleTime;
    /**
     * 创建时间
     */
    //@JsonIgnore
    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
//    @JsonIgnore
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;

}
