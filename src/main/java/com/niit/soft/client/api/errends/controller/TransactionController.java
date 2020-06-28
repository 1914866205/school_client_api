package com.niit.soft.client.api.errends.controller;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.errends.domain.dto.FinshOrderDto;
import com.niit.soft.client.api.errends.domain.dto.TransactionDto;
import com.niit.soft.client.api.errends.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wl
 * @ClassNameTransactionController
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "跑腿接口")
@RequestMapping("/transaction")
public class TransactionController {
    @Resource
   private TransactionService transactionService;
    @ApiOperation(value = "抢到订单 ",notes = "请求参数为订单dto")
    @PostMapping("/order")
    public ResponseResult insertDeliveryOrder(@RequestBody TransactionDto transactionDto){
        return transactionService.insertTransaction(transactionDto);
    }


    @ApiOperation(value = "取货",notes = "请求参数是订单dto")
    @PostMapping("/getgoods")
    public ResponseResult getGoods(@RequestBody TransactionDto transactionDto) {
        return transactionService.getGoods(transactionDto);
    }

    @ApiOperation(value = "完成订单", notes = "请求参数是订单id")
    @PostMapping("/finshOrder")
    public ResponseResult finshOrder(@RequestBody TransactionDto transactionDto) {
        return transactionService.finshOrder(transactionDto.getOrderId());
    }


    @ApiOperation(value = "查询自己所有订单", notes = "请求参数是订单dto")
    @PostMapping("/errends/order")
    public ResponseResult selectErrendsOrder(@RequestBody FinshOrderDto finshOrderDto) {
        return transactionService.selctTransactionOrder(finshOrderDto);
    }

}
