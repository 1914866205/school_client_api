package com.niit.soft.client.api.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.niit.soft.client.api.domain.model.FleaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/9
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaGoodsVo {
    /**
     * 商品id
     */
    private Long pkFleaGoodsId;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 商品描述
     */
    private String goodsDescription;
    /**
     * 商品图片
     */
    private String goodsImgUrl;
    /**
     * 商品价格
     */
    private Double goodsPrice;
    /**
     * 商品标签
     */
    private String goodsMark;
    /**
     * 商品发布时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date goodsCreateTime;
    /**
     * 商品类型id
     */
    private Long pkFleaTypeId;
    /**
     * 分类名称
     */
    private String typeName;
    /**
     * 商品发布人id
     */
    private Long pkFleaUserId;
    /**
     * 商品发布人昵称
     */
    private String nickname;
    /**
     * 商品发布人名字
     */
    private String username;
    /**
     * 商品发布人头像
     */
    private String userAvatar;
    /**
     * 删除标记
     */
    private Boolean isDeleted;
}
