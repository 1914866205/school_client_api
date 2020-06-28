package com.niit.soft.client.api.domain.dto.schoolmate;

import lombok.Builder;
import lombok.Data;

/**
 * @author Yujie_Zhao
 * @ClassName ReplyCommentDto
 * @Description TODO
 * @Date 2020/6/9  16:51
 * @Version 1.0
 **/
@Data
@Builder
public class ReplyCommentDto {
    private String commentId;
    private String userId;
    private String content;
}
