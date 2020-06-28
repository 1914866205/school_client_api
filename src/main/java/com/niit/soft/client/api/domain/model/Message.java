package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author Tao
 * @version 1.0
 * @ClassName Message
 * @Description TODO
 * @date 2020-05-26 14:47
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_message_id", nullable = false)
    private Long pkMessageId;

    /**
     * 消息内容
     */
    @Column(name = "content", nullable = false)
    private String content;

    /**
     * 阅读状态( 0 表示未读，1表示已读)
     */
    @Column(name = "is_readed", nullable = false)
    private Boolean isReaded;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create", nullable = false)
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @Column(name = "gmt_modified", nullable = false)
    private Timestamp gmtModified;
}
