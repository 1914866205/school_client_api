package com.niit.soft.client.api.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author su
 * @className Job
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 **/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job extends Model<Job> {

    /**
     * 主键
     */
    @Id
    @TableId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkJobId;

    @TableField("name")
    @Column(nullable = false, length = 60)
    private String name;

    @TableField("description")
    @Column(nullable = false)
    private String description;

    @TableField("boss")
    @Column(nullable = false, length = 60)
    private String boss;

    @TableField("boss_phone")
    @Column(nullable = false, length = 60)
    private String bossPhone;

    @TableField("boss_avatar")
    @Column(nullable = false, length = 60)
    private String bossAvatar;

    @TableField("company_id")
    @Column(length = 20)
    private Long companyId;

    @TableField("workplace")
    @Column(nullable = false, length = 60)
    private String workplace;

    @TableField("working_time")
    @Column(nullable = false, length = 60)
    private String workingTime;

    @TableField("pay")
    @Column(length = 10)
    private BigDecimal pay;

    @TableField("min")
    @Column(length = 10)
    private Integer min;

    @TableField("max")
    @Column(length = 10)
    private Integer max;

    @TableField("experience")
    @Column(length = 20)
    private String experience;

    @TableField("diploma")
    @Column(length = 20)
    private String diploma;

    @TableField("job_type_id")
    @Column(length = 20)
    private Long jobTypeId;

    @TableField("number")
    @Column(length = 4)
    private Integer number;

    @TableField("resumes")
    private String resumes;
    /**
     * 删除标志
     */
    @TableField("is_deleted")
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @TableField("gmt_modified")
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp gmtModified;


}
