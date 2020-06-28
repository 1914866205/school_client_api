package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;

/**
 * @author Tao
 */
public interface SysSemesterService {
    /**
     * 查询所有学期数据
     *
     * @return
     */
    ResponseResult findAllSysSemester();
}
