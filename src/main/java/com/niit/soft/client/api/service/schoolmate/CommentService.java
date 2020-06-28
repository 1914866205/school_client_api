package com.niit.soft.client.api.service.schoolmate;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.schoolmate.CommentDto;

/**
 * @author Yujie_Zhao
 * @ClassName CommentService
 * @Description 评论
 * @Date 2020/6/8  16:21
 * @Version 1.0
 **/
public interface CommentService {
    /**
     * 添加评论
     *
     * @param commentDto
     * @return
     */
    ResponseResult insertComment(CommentDto commentDto);

    /**
     * 删除评论
     *
     * @param commentId
     * @return
     */
    ResponseResult deleteComment(String commentId);
}
