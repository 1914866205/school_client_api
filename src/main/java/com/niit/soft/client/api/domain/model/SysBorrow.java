package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @ClassName SysBorrow
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/25
 **/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysBorrow {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkBorrowId;

    /**
     * 借阅人姓名
     */
    @Column(nullable = false, length = 50)
    private String borrowUserName;

    /**
     * 借阅人姓名
     */
    @Column(nullable = false, length = 32)
    private String borrowUserNumber;

    /**
     * 借阅人手机号
     */
    @Column(nullable = false, length = 32)
    private String borrowUserPhone;

    /**
     * 借阅图书名称
     */
    @Column(nullable = false, length = 50)
    private String borrowBookName;

    /**
     * 借阅图书编号
     */
    @Column(nullable = false, length = 50)
    private String borrowBookId;

    /**
     * 归还状态  0 未归还， 1 已归还）
     */
    @Column(nullable = false, length = 4)
    private Boolean isReturned;

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