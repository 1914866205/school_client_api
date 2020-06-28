package com.niit.soft.client.api.service.schoolmate;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.schoolmate.*;
import com.niit.soft.client.api.domain.model.schoolmate.Dynamic;
import com.niit.soft.client.api.domain.model.schoolmate.DynamicPhoto;
import com.niit.soft.client.api.domain.vo.schoolmate.CommentVo;
import com.niit.soft.client.api.domain.vo.schoolmate.DynamicVo;

import java.util.List;

/**
 * @ClassName DynamicService
 * @Description 好友圈模块动态资讯
 * @Author xiaobinggan
 * @Date 2020/6/8 8:10 下午
 * @Version 1.0
 **/
public interface DynamicService {
    /**
     * 根据id查找动态资讯
     *
     * @param id
     * @return
     */
    DynamicVo findDynamicVoById(String id);

    /**
     * 根据用户id查找动态资讯
     *
     * @param id
     * @return
     */
    List<Dynamic> findDynamicVoByUserId(SchoolmateUserPageDto schoolmateUserPageDto);


    /**
     * 根据动态id查询图片
     *
     * @param id
     * @return
     */
    List<DynamicPhoto> findDynamicPhotoById(String id);


    /**
     * 查找所有资讯
     *
     * @param schoolmatePageDto
     * @return
     */
    List<Dynamic> findDynamicByPage(SchoolmatePageDto schoolmatePageDto);

    /**
     * 删除动态
     *
     * @param dynamic
     * @return
     */
    int deleteDynamicById(Dynamic dynamic);


    /**
     * 根据id查找多层级评论
     *
     * @param id
     * @return
     */
    CommentVo findCommentVoById(String id);

    /**
     * 添加新的动态
     *
     * @param dynamicDto
     */
    Dynamic addOne(DynamicDto dynamicDto);

    /**
     * @param dynamicPhotoDtos
     * @return
     */
    ResponseResult addPhoto(List<DynamicPhotoDto> dynamicPhotoDtos);

    /**
     * 点赞
     *
     * @param thumbDto
     */
    ResponseResult thumbsUp(ThumbDto thumbDto);

}
