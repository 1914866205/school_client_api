package com.niit.soft.client.api.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author su
 * @className company
 * @Description TODO
 * @Date 2020/6/11
 * @Version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Table(name = "company")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company extends Model<Company> {


    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkCompanyId;

    @TableField("name")
    @Column(nullable = false, length = 60)
    private String name;

    @TableField("tag")
    @Column(nullable = false)
    private String tag;

    @TableField("logo")
    private String logo;

    @TableField("workers")
    @Column(nullable = false, length = 20)
    private Integer workers;

    @TableField("type")
    @Column(length = 60)
    private String type;

    @TableField("description")
    private String description;

    @TableField("working_time")
    @Column(nullable = false, length = 60)
    private String workingTime;

    @TableField("working_status")
    @Column(nullable = false, length = 60)
    private String workingStatus;

    @TableField("job_numbers")
    @Column(length = 10)
    private Integer jobNumbers;

    @TableField("vacation")
    @Column(length = 10)
    private String vacation;

    @TableField("address")
    private String address;

    @TableField("longitude")
    @Column(length = 60)
    private String longitude;

    @TableField("latitude")
    @Column(length = 60)
    private String latitude;

    /**
     * 删除标志
     */
    @TableField("is_deleted")
    @Column(nullable = false, length = 2)
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
