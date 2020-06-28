package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.PageDto;

/**
 * @author Yujie_Zhao
 * @ClassName InfoTypeService
 * @Description 资讯分类Service接口
 * @Date 2020/5/26  22:02
 * @Version 1.0
 **/
public interface InfoTypeService {

    /**
     * 查询所有的资讯分类
     *
     * @return
     */
    ResponseResult getAllType();


    /**
     * 根据分类id查询相对应的数据
     *
     * @param pageDto
     * @return
     */
    ResponseResult findInfoByTypeId(PageDto pageDto);
}
