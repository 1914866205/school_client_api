package com.niit.soft.client.api.domain.vo.schoolmate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @ClassName Reply_commentVo
 * @Description 回复视图
 * @Author xiaobinggan
 * @Date 2020/6/19 10:38 上午
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCommentVo {
    private String pkReplyCommentId;

    private String commentId;

    private String userId;

    private String avatar;

    private String nickname;

    private String content;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private Boolean isDeleted;
}
