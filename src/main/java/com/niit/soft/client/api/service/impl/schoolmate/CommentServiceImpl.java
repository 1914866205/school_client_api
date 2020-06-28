package com.niit.soft.client.api.service.impl.schoolmate;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.schoolmate.CommentDto;
import com.niit.soft.client.api.domain.model.schoolmate.Comment;
import com.niit.soft.client.api.domain.model.schoolmate.Dynamic;
import com.niit.soft.client.api.domain.model.schoolmate.ReplyComment;
import com.niit.soft.client.api.repository.schoolmate.CommentRepository;
import com.niit.soft.client.api.repository.schoolmate.DynamicRepository;
import com.niit.soft.client.api.repository.schoolmate.ReplyCommentRepository;
import com.niit.soft.client.api.service.schoolmate.CommentService;
import com.niit.soft.client.api.util.SnowFlake;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName CommentServiceImpl
 * @Description 评论
 * @Date 2020/6/8  16:21
 * @Version 1.0
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentRepository commentRepository;

    @Resource
    private DynamicRepository dynamicRepository;

    @Resource
    private ReplyCommentRepository replyCommentRepository;

    @Override
    public ResponseResult insertComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setPkCommentId(String.valueOf(new SnowFlake(1, 3).nextId()));
        comment.setIsDeleted(false);
        comment.setContent(commentDto.getContent());
        comment.setDynamicId(commentDto.getDynamicId());
        comment.setUserId(commentDto.getUserId());
        commentRepository.save(comment);
        //获取获取一动态的评论数量
        List<Comment> commentList = commentRepository.findCommentByIsDeletedAndDynamicId(false, commentDto.getDynamicId());
        Dynamic dynamic = dynamicRepository.findDynamicByPkDynamicId(comment.getDynamicId());
        dynamic.setComments(commentList.size());
        //修改动态评论数量
        dynamicRepository.saveAndFlush(dynamic);
        return ResponseResult.success("添加成功");
    }

    @Override
    public ResponseResult deleteComment(String commentId) {
        Comment comment = commentRepository.findCommentByPkCommentId(commentId);
        comment.setIsDeleted(true);
        commentRepository.saveAndFlush(comment);
        List<ReplyComment> replyCommentList = replyCommentRepository.findByCommentId(commentId);
        replyCommentList.forEach(replyComment -> {
            ReplyComment replyComment1 = replyCommentRepository.findReplyCommentByPkReplyCommentId(replyComment.getPkReplyCommentId());
            replyComment1.setIsDeleted(true);
            replyCommentRepository.saveAndFlush(replyComment1);
        });
        //获取获取一动态的评论数量
        List<Comment> commentList = commentRepository.findCommentByIsDeletedAndDynamicId(false, comment.getDynamicId());
        Dynamic dynamic = dynamicRepository.findDynamicByPkDynamicId(comment.getDynamicId());
        dynamic.setComments(commentList.size());
        //修改动态评论数量
        dynamicRepository.saveAndFlush(dynamic);
//        List<Long> ids = replyCommentRepository.selectAllCommentId(commentId);
//        replyCommentRepository.updateIsDelete(ids);
        return ResponseResult.success("删除成功");
    }

}
