package com.niit.soft.client.api.mapper.schoolmate;

import com.niit.soft.client.api.domain.vo.schoolmate.CommentVo;

/**
 * @ClassName CommentMapper
 * @Description 好友圈评论
 * @Author xiaobinggan
 * @Date 2020/6/8 8:33 下午
 * @Version 1.0
 **/
public interface CommentMapper {
    /**
     * 根据id查找评论
     *
     * @param id
     * @return
     */
    CommentVo findCommentVoById(String id);


}
