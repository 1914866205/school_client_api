package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.FleaCommentDto;
import com.niit.soft.client.api.domain.dto.FleaRewardDto;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaGoodsService.java
 * @Description TODO
 * @createTime 2020年06月09日 13:58:00
 */
public interface FleaCommentService {
    /**
     * 新增评论
     *
     * @param commentDto FleaCommentDto
     * @return ResponseResult
     */
    ResponseResult addComment(FleaCommentDto commentDto);

    /**
     * 根据悬赏id查询评论信息
     *
     * @param fleaRewardDto FleaRewardDto
     * @return ResponseResult
     */
    ResponseResult getCommentByRewardId(FleaRewardDto fleaRewardDto);
}
