package com.niit.soft.client.api.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaOrderVo {
    private String orderId;
    private String goodsName;
    private Double goodsPrice;
    private String goodsSeller;
    private String goodsBuyer;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date orderCreateTime;
    private String goodsDescription;
    private String goodsMark;
    private String goodsImg;
}
