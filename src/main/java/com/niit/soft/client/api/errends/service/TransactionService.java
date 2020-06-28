package com.niit.soft.client.api.errends.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.errends.domain.dto.FinshOrderDto;
import com.niit.soft.client.api.errends.domain.dto.TransactionDto;
import com.niit.soft.client.api.errends.domain.vo.DeliveryOderInformationVo;

import java.util.List;

/**
 * @author wl
 * @ClassNameTransaction
 * @Description 交易表
 * @Date 2020/6/9
 * @Version 1.0
 */
public interface TransactionService {
    /**
     * 新增交易信息 订单交易
     *
     * @param transactionDto
     * @return
     */
    ResponseResult insertTransaction(TransactionDto transactionDto);

    /**
     * 完成订单
     *
     * @param orderId
     * @return
     */
    ResponseResult finshOrder(String orderId);

    /**
     * 取货
     *
     * @param transactionDto
     * @return
     */

  ResponseResult getGoods(TransactionDto  transactionDto);

    /**
     * 查询我抢的订单
     * @param finshOrderDto
     * @return
     */
    ResponseResult selctTransactionOrder(FinshOrderDto finshOrderDto);



}
