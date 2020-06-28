package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.PageDto;

/**
 * @author Yujie_Zhao
 * @ClassName InfoManageService
 * @Description 资讯Service
 * @Date 2020/5/26  11:39
 * @Version 1.0
 **/
public interface InfoManageService {

    /**
     * 分页查询所有咨询
     *
     * @param pageDto
     * @return PageDto pageDto
     */
    ResponseResult findAllInfoByPage(PageDto pageDto);


    /**
     * 查询置顶资讯
     *
     * @param pageDto
     * @return
     */
    ResponseResult getIsTopInfo(PageDto pageDto);


    /**
     * 通过id查询咨询详情
     *
     * @param id
     * @return
     */
    ResponseResult findInfoManageById(Long id);

}
