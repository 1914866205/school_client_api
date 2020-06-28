package com.niit.soft.client.api.service.impl.schoolmate;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.schoolmate.ReplyCommentDto;
import com.niit.soft.client.api.domain.model.schoolmate.DynamicPhoto;
import com.niit.soft.client.api.domain.model.schoolmate.ReplyComment;
import com.niit.soft.client.api.mapper.schoolmate.DynamicMapper;
import com.niit.soft.client.api.mapper.schoolmate.DynamicPhotoMapper;
import com.niit.soft.client.api.mapper.schoolmate.ReplyCommentMapper;
import com.niit.soft.client.api.repository.schoolmate.ReplyCommentRepository;
import com.niit.soft.client.api.service.schoolmate.ReplyCommentService;
import com.niit.soft.client.api.util.SnowFlake;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Yujie_Zhao
 * @ClassName ReplyCommentServiceImpl
 * @Description TODO
 * @Date 2020/6/9  9:59
 * @Version 1.0
 **/
@Service
public class ReplyCommentServiceImpl extends ServiceImpl<ReplyCommentMapper, ReplyComment> implements ReplyCommentService {
    @Resource
    private ReplyCommentRepository replyCommentRepository;

    @Override
    public ResponseResult insertReplyComment(ReplyCommentDto replyCommentDto) {
        ReplyComment replyComment = new ReplyComment();
        replyComment.setPkReplyCommentId(String.valueOf(new SnowFlake(1, 3).nextId()));
        replyComment.setCommentId(replyCommentDto.getCommentId());
        replyComment.setContent(replyCommentDto.getContent());
        replyComment.setUserId(replyCommentDto.getUserId());
        replyComment.setIsDeleted(false);
        //添加评论
        replyCommentRepository.save(replyComment);

        //更具评论的找到对应动态
//        Dynamic dynamic = dynamicRepository.findDynamicByPkDynamicId(commentRepository.findDynamicIdByPkCommentId(replyComment.getCommentId()));
        //更新动态的评论数量
//        dynamic.setComments(dynamic.getComments() + 1);
//        dynamicRepository.saveAndFlush(dynamic);
        return ResponseResult.success("添加评论的评论成功");

    }

    @Override
    public ResponseResult deleteReplyComment(String id) {
        ReplyComment replyComment = replyCommentRepository.findReplyCommentByPkReplyCommentId(id);
        replyComment.setIsDeleted(true);
        replyCommentRepository.saveAndFlush(replyComment);

        return ResponseResult.success("删除评论的评论成功");
    }
}
