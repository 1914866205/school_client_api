package com.niit.soft.client.api.domain.model.schoolmate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @ClassName Message
 * @Description 聊天记录
 * @Author xiaobinggan
 * @Date 2020/6/17 8:40 上午
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String pkMessageId;
    private String fromId;
    private String toId;
    private String content;
    private String destination;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
}
