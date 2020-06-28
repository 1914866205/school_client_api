package com.niit.soft.client.api.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-06-09 15:15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo {
    private Long goodsId;
    private String goodsName;
    private Double goodsPrice;
    private String goodsDescription;
    private String goodsMarks;
    private String goodsImgUrl;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date goodsCreateTime;
    private String goodsType;
    private String goodsUserName;
    private Boolean isDeleted;
}
