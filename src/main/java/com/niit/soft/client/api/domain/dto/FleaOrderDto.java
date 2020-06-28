package com.niit.soft.client.api.domain.dto;

/**
 * 描述:
 * 新增订单传参类
 *
 * @author：Guorc
 * @create 2020-06-11 13:43
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaOrderDto.java
 * @Description TODO
 * @createTime 2020年06月11日 14:39:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FleaOrderDto {
    private String pkFleaOrderId;
    private Long fleaUserBuyerPkFleaUserId;
    private Long fleaGoodsPkFleaGoodsId;
    private Long fleaUserSellerPkFleaUserId;
}
