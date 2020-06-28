package com.niit.soft.client.api.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
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
@EqualsAndHashCode(callSuper = true)
@Table(name = "part_job")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartJob extends Model<PartJob> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @TableId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkPartJobId;

    @TableField("name")
    private String name;

    @TableField("description")
    @Column(nullable = false)
    private String description;

    @Column(nullable = false,length = 60)
    private  String bossId;

    @TableField("boss_name")
    @Column(nullable = false, length = 60)
    private String bossName;

    @TableField("boss_phone")
    @Column(nullable = false, length = 60)
    private String bossPhone;

    @TableField("boss_avatar")
    @Column(nullable = false)
    private String bossAvatar;

    @TableField("workplace")
    @Column(nullable = false, length = 60)
    private String workplace;

    @TableField("working_date")
    @Column(nullable = false, length = 60)
    private String workingDate;

    @TableField("working_time")
    @Column(nullable = false, length = 60)
    private String workingTime;

    @TableField("pay")
    @Column(length = 10)
    private BigDecimal pay;

    @TableField("pay_type")
    @Column(length = 20)
    private String payType;

    @TableField("job_type")
    @Column(length = 20)
    private String jobType;

    @TableField("number")
    @Column(length = 10)
    private Integer number;

    @TableField("have")
    @Column(length = 10)
    private Integer have;

    @TableField("need")
    @Column(length = 10)
    private Boolean need;

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
