package com.niit.soft.client.api.domain.dto.schoolmate;

import lombok.Builder;
import lombok.Data;

/**
 * @author Yujie_Zhao
 * @ClassName CommentDto
 * @Description 评论
 * @Date 2020/6/8  16:52
 * @Version 1.0
 **/
@Data
@Builder
public class CommentDto {
    private String dynamicId;
    private String userId;
    private String content;

}
