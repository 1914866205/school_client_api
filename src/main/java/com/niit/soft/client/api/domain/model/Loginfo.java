package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Tao
 * @version 1.0
 * @ClassName Loginfo
 * @Description TODO
 * @date 2020-05-29 21:01
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Loginfo {

    /**
     * 日志id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkLoginfoId;

    /**
     * 执行时间
     */
    @Column(nullable = false)
    private Timestamp executionTime;

    /**
     * 执行类
     */
    @Column(nullable = false)
    private String className;

    /**
     * 内容
     */
    @Column(nullable = false)
    private String content;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @Column(nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志
     */
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;
}
