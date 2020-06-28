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
 * @ClassName SysCourse
 * @Description 课程管理实体
 * @Date 2020/5/25  22:32
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sys_course", indexes = {@Index(name = "schedule_idIndex", columnList = "schedule_id")})
public class SysCourse {
    /**
     * 主键，策略为自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkCourseId;

    /**
     * 课表id
     * nullable = false为非空约束，unique = true是唯一约束
     */
    @Column(name = "schedule_id", nullable = false)
    private Long scheduleId;

    /**
     * 科目id
     */
    @Column(name = "subject_id", nullable = false)
    private Long subjectId;

    /**
     * 教师工号
     */
    @Column(name = "user_job_number", nullable = false, length = 20)
    private String userJobNumber;

    /**
     * 周次（例如 1-5，8-13）
     */
    @Column(name = "week_duration", nullable = false)
    private String weekDuration;

    /**
     * 房间id
     */
    @Column(name = "room_id", nullable = false, length = 11)
    private Integer roomId;

    /**
     * 周几
     */
    @Column(name = "week_day", nullable = false, length = 4)
    private Integer weekDay;

    /**
     * 上课的时间段（
     * <p>
     * 1  →  上午 1-2节
     * <p>
     * 2 → 上午 3-4节
     * <p>
     * ）
     */
    @Column(name = "time", nullable = false, length = 4)
    private Integer time;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create", nullable = false)
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @UpdateTimestamp
    @Column(name = "gmt_modified", nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
    @Column(name = "is_deleted", nullable = false, length = 4)
    private Boolean isDeleted;
}
