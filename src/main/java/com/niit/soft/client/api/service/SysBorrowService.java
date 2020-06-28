package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;

/**
 * @author Tao
 */
public interface SysBorrowService {


    /**
     * 查询借阅图书的情况
     *
     * @param borrowUserNumber
     * @return
     */
    ResponseResult findBorrowMessage(String borrowUserNumber);
}
