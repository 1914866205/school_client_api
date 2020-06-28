package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Yujie_Zhao
 * @ClassName SchoolTimetable
 * @Description 课表管理
 * @Date 2020/5/25  22:40
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "schedule", indexes = {@Index(name = "clazz_idIndex", columnList = "clazz_id")})
public class Schedule {
    /**
     * 主键，策略为自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkSchoolTimetableId;

    /**
     * 班级id
     */
    @Column(name = "clazz_id", nullable = false)
    private Long clazzId;

    /**
     * 学期id
     */
    @Column(nullable = false)
    private Long semesterId;

    /**
     * 第几周（第一周、第二周...）
     */
    @Column(nullable = false, length = 11)
    private Integer week;

    /**
     * 课表名称
     */
    @Column(nullable = false, length = 60)
    private String name;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;
}
