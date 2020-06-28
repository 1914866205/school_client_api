package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Tao
 * @version 1.0
 * @ClassName Clazz
 * @Description TODO
 * @date 2020-05-25 22:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "clazz", indexes = {@Index(name = "nameIndex", columnList = "name"),
        @Index(name = "college_idIndex", columnList = "college_id")})
public class Clazz {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_clazz_id", nullable = false)
    private Long pkClazzId;

    /**
     * 名称
     */
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    /**
     * 年级
     */
    @Column(name = "grade", nullable = false, length = 30)
    private String grade;

    /**
     * 学院id
     */
    @Column(name = "college_id", nullable = false)
    private Long collegeId;

    /**
     * 班主任工号
     */
    @Column(name = "clazz_headmaster", nullable = false, length = 20, unique = true)
    private String clazzHeadmaster;

    /**
     * 更新时间
     */
    @UpdateTimestamp
    @Column(name = "gmt_modified", nullable = false)
    private Timestamp gmtModified;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create", nullable = false)
    private Timestamp gmtCreate;

    /**
     * 删除标志
     */
    @Column(name = "is_deleted", nullable = false, length = 4)
    private Boolean isDeleted;
}
