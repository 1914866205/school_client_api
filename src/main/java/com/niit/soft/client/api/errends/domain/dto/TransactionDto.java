package com.niit.soft.client.api.errends.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wl
 * @ClassNameTransactionDto
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    /**
     * 跑腿人的id
     */
    private String errandsId;
    /**
     * 订单id
     */

    private  String orderId;



}
