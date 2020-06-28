package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.FleaOrderDto;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaGoodsService.java
 * @Description TODO
 * @createTime 2020年06月09日 13:58:00
 */
public interface FleaOrderService {
    /**
     * 新增订单
     *
     * @param fleaOrderDto FleaOrderDto
     * @return ResponseResult
     */
    ResponseResult orderIncreased(FleaOrderDto fleaOrderDto);

    /**
     * 逻辑删除订单
     *
     * @param fleaOrderDto FleaOrderDto
     * @return ResponseResult
     */
    ResponseResult logicalDel(FleaOrderDto fleaOrderDto);
}
