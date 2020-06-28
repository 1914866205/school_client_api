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
 * @create 2020-06-10 10:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionVo {
    private Long userId;
    private Long goodsId;
    private String goodsName;
    private String goodsDescription;
    private Double goodsPrice;
    private String goodsImgUrl;
    private String goodsMark;
    private String username;
    private String phoneNumber;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
}
