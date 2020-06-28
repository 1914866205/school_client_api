package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.PageDto;

/**
 * @author Tao
 */
public interface MessageService {
    /**
     * 分页查询消息数据
     *
     * @param pageDto
     * @return
     */
    ResponseResult findAllByPage(PageDto pageDto);


}
