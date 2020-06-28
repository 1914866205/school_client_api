package com.niit.soft.client.api.errends.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.errends.domain.dto.DeliveryOrderDto;
import com.niit.soft.client.api.errends.domain.dto.FinshOrderDto;

import java.sql.SQLException;
import java.util.Map;

/**
 * @author wl
 * @ClassNameDeliveryOrederService
 * @Description 跑腿订单模块
 * @Date 2020/6/9
 * @Version 1.0
 */
public interface DeliveryOrederService {
    /**
     * 新增订单
     *
     * @return
     * @throws SQLException
     */

    ResponseResult insertOrder(DeliveryOrderDto deliveryOrderDto);

    /**
     * 取消订单
     *
     * @return
     */
    ResponseResult cancleOrder(String id);


    /**
     * 查询个人发布已经完成订单 或者取消订单
     */
    Map<String, Object> selectFinshOrder(FinshOrderDto finshOrderDto);

    /**
     * 逻辑删除订单
     */
    ResponseResult deleteOrder(String id);

    /**
     * 查询所有未被抢单的订单 按照时间排序
     *
     * @return
     */
    ResponseResult selectAllOrder(FinshOrderDto finshOrderDto);
}
