package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.FleaCommentDto;
import com.niit.soft.client.api.domain.dto.FleaRewardDto;
import com.niit.soft.client.api.domain.model.FleaComment;
import com.niit.soft.client.api.repository.FleaCommentRepository;
import com.niit.soft.client.api.repository.FleaRewardRepository;
import com.niit.soft.client.api.repository.FleaUserRepository;
import com.niit.soft.client.api.service.FleaCommentService;
import com.niit.soft.client.api.service.FleaGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaCommentServiceImpl.java
 * @Description TODO
 * @createTime 2020年06月09日 14:06:00
 */
@Slf4j
@Service
public class FleaCommentServiceImpl implements FleaCommentService {
    @Autowired
    private FleaCommentRepository commentRepository;
    @Autowired
    private FleaUserRepository userRepository;
    @Autowired
    private FleaRewardRepository rewardRepository;

    @Override
    public ResponseResult addComment(FleaCommentDto commentDto) {
        FleaComment fleaComment = FleaComment.builder()
                .comment(commentDto.getComment())
                .commentBy(userRepository.getOne(commentDto.getUserId()))
                .reviewer(userRepository.getOne(commentDto.getReviewerId()))
                .createTime(Timestamp.valueOf(LocalDateTime.now()))
                .fleaReward(rewardRepository.getOne(commentDto.getRewardId()))
                .isDeleted(false)
                .build();
        commentRepository.save(fleaComment);
        return ResponseResult.success("成功");
    }

    @Override
    public ResponseResult getCommentByRewardId(FleaRewardDto fleaRewardDto) {
        return ResponseResult.success(commentRepository.selectByRewardId(fleaRewardDto));
    }
}
