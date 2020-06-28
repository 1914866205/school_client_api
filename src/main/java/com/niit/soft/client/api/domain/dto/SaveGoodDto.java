package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description 添加商品Dto
 * @Date 2020/6/10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveGoodDto {
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
     * 商品类型id
     */
    private Long pkFleaTypeId;
    /**
     * 商品发布人id
     */
    private Long pkFleaUserId;
}
