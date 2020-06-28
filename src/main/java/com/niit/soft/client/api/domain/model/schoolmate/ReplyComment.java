package com.niit.soft.client.api.domain.model.schoolmate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Yujie_Zhao
 * @ClassName ReplyComment
 * @Description 校友圈的评论的回复
 * @Date 2020/6/8  14:01
 * @Version 1.0
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "reply_comment")
public class ReplyComment {
    /**
     * 主键，策略为自增
     */
    @Id
    private String pkReplyCommentId;


    /**
     * 动态id
     */
    @Column(name = "comment_id", nullable = false)
    private String commentId;

    /**
     * 用户id
     */
    @Column(name = "user_id", nullable = false)
    private String userId;

    /**
     * 评论内容
     */
    @Column(name = "content", nullable = false)
    private String content = "";

    /**
     * 创建时间
     */
    @CreatedDate
    @JsonIgnore

    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "gmt_modified", nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
    @Column(name = "is_deleted", nullable = false, length = 4)
    private Boolean isDeleted;
}
