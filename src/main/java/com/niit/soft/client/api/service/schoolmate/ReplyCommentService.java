package com.niit.soft.client.api.service.schoolmate;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.schoolmate.ReplyCommentDto;
import com.niit.soft.client.api.domain.model.schoolmate.ReplyComment;

/**
 * @author Yujie_Zhao
 * @ClassName ReplyCommentService
 * @Description 评论的评论
 * @Date 2020/6/9  8:36
 * @Version 1.0
 **/
public interface ReplyCommentService extends IService<ReplyComment> {
    /**
     * 添加
     *
     * @param replyCommentDto
     * @return
     */
    ResponseResult insertReplyComment(ReplyCommentDto replyCommentDto);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    ResponseResult deleteReplyComment(String id);

}
