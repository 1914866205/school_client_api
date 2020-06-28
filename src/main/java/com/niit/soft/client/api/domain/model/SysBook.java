package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @ClassName SysBook
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/25
 **/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysBook {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkBookId;

    /**
     * 作者
     */
    @Column(nullable = false, length = 60)
    private String author;

    /**
     * 封面
     */
    @Column(nullable = false, unique = true)
    private String cover;

    /**
     * 类型
     */
    @Column(nullable = false, length = 32)
    private String type;

    /**
     * 简介
     */
    @Column(nullable = false)
    private String description;

    /**
     * 库存总数
     */
    @Column(nullable = false)
    private Integer bookNumber;

    /**
     * 库存剩余
     */
    @Column(nullable = false)
    private Integer bookResidueNumber;

    /**
     * 状态  0已上架/1未上架/2即将上架
     */
    @Column(nullable = false)
    private Integer status;

    /**
     * 书名
     */
    @Column(nullable = false)
    private String bookName;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp gmtModified;

    /**
     * 删除标志
     */
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;
}