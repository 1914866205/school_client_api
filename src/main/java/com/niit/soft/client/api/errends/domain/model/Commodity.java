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
 * @ClassNamecommodity
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
@Table(name = "commodity")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Commodity {
    /**
     * 主键自增
     */
    @Id
    @Column(name = "id" ,length = 30)
    private String id;
    /**
     * 商品类型
     */
    @Column(name = "type", nullable = false)
    private String type;
    /**
     * 物品价格范围
     */
    @Column(name = "price_rang", nullable = false)
    private String priceRang;
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
