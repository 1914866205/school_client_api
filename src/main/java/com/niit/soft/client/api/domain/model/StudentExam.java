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
 * @ClassName StudentExam
 * @Description 学生考务关联表
 * @Date 2020/5/25  22:54
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student_exam", indexes = {@Index(name = "user_job_numberIndex", columnList = "user_job_number")})
public class StudentExam {
    /**
     * 主键，策略为自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkStudentExamId;

    /**
     * 考务id
     */
    @Column(nullable = false)
    private Long examinationId;

    /**
     * 真实姓名
     */
    @Column(name = "user_job_number", nullable = false, length = 20)
    private String userJobNumber;


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


    /**
     * 成绩
     */
    @Column(precision = 5, scale = 2)
    private Double performance;
}
