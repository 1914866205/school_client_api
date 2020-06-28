package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/29
 **/
public interface OrderService {
    /**
     * 查询清单明细
     *
     * @param jobNumber
     * @return
     */
    ResponseResult findALLByJobNumer(String jobNumber);
}
