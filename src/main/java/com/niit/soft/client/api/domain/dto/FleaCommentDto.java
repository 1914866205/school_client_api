package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 * 新增评论的参数
 *
 * @author：Guorc
 * @create 2020-06-11 16:04
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaCommentDto {
    private String comment;
    private Long userId;
    private Long rewardId;
    private Long reviewerId;
}
