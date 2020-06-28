package com.niit.soft.client.api.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Many;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName a.java
 * @Description TODO
 * @createTime 2020年06月09日 11:26:00
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaGoods {

    /**
     * 商品id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkFleaGoodsId;

    /**
     * 商品名称
     */
    @Column(nullable = false, length = 32)
    private String goodsName;

    /**
     * 商品描述
     */
    @Column(nullable = false)
    private String goodsDescription;

    /**
     * 商品图片
     */
    @Column(nullable = false)
    private String goodsImgUrl;

    /**
     * 商品价格
     */
    @Column(nullable = false)
    private Double goodsPrice;
    /**
     * 商品标签
     */
    @Column(nullable = false)
    private String goodsMark;

    /**
     * 商品发布时间
     */
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp goodsCreateTime;

    /**
     * 商品发布人id
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private FleaUser fleaUser;

    /**
     * 商品类型id
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private FleaType fleaType;

    /**
     * 删除标志
     */
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;

}
