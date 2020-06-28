package com.niit.soft.client.api.domain.vo.schoolmate;

import com.niit.soft.client.api.domain.model.schoolmate.ReplyComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName CommentVo
 * @Description 评论视图
 * @Author xiaobinggan
 * @Date 2020/6/8 2:56 下午
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private String pkCommentId;
    private String dynamicId;
    private String userId;
    private String avatar;
    private String nickname;
    private String content;
    private Timestamp gmtCreate;
    private Boolean isDeleted;
    //    private Comment comment;
    private List<ReplyComment> replyComments;
    private List<ReplyCommentVo> replyCommentVos;
}
