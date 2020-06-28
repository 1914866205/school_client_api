package com.niit.soft.client.api.service;


import java.util.Map;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.dto.TypeDto;
import org.springframework.data.domain.Pageable;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaGoodsService.java
 * @Description TODO
 * @createTime 2020年06月09日 13:58:00
 */
public interface FleaTypeService {
    /**
     * 查询所有的类型
     *
     * @return Map<String, Object>
     */
    Map<String, Object> findAllType();


    /**
     * 根据分类id查询商品
     *
     * @param typeDto TypeDto
     * @return ResponseResult
     */
    ResponseResult getGoodsByType(TypeDto typeDto);
    /**
     * 查询4个的类型
     *
     * @return Map<String, Object>
     */
    Map<String,Object>findTopType();
}
