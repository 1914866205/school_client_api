package com.niit.soft.client.api.service.schoolmate;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.dto.schoolmate.DynamicCollectionInDto;

/**
 * @author Yujie_Zhao
 * @ClassName CollectionsService
 * @Description 收藏
 * @Date 2020/6/11  9:49
 * @Version 1.0
 **/
public interface CollectionsService {

    /**
     * 根据用户id查找所有收藏动态
     *
     * @param id
     * @return
     */
    ResponseResult findCollections(String id);

    /**
     * 分页查询所有咨询
     *
     * @param pageDto
     * @return PageDto pageDto
     */
    ResponseResult getCollectionsByUserId(PageDto pageDto);

    /**
     * 添加加收藏
     *
     * @param dynamicCollectionInDto
     * @return
     */
    ResponseResult insertCollections(DynamicCollectionInDto dynamicCollectionInDto);


    /**
     * 删除用户以收藏的动态资讯
     *
     * @param id
     * @return
     */
    ResponseResult updateCollectionsIsDelete(String id);
}
