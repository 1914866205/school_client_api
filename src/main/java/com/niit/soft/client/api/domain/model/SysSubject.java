package com.niit.soft.client.api.domain.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @ClassName SysSubject
 * @Description 科目表
 * @date 2020-05-25 22:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SysSubject {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_subject_id", nullable = false)
    @TableId
    private Long pkSubjectId;

    /**
     * 名称
     */
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    /**
     * 课程类型
     */
    @Column(name = "type", nullable = false, length = 20)
    private String type;

    /**
     * 科目图片
     */
    @Column(name = "cover", nullable = false)
    private String cover;

    /**
     * 背景颜色
     */
    @Column(name = "background_color", nullable = false)
    private String backgroundColor;


    /**
     * 创建时间
     */
    @JsonIgnore
    @Column(name = "gmt_create", nullable = false)
    private Timestamp gmt_create;

    /**
     * 更新时间
     */
    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "gmt_modified", nullable = false)
    private Timestamp gmt_modified;

    /**
     * 删除标志
     */
    @JsonIgnore
    @Column(name = "is_deleted", nullable = false, length = 4)
    private Boolean isDeleted;
}
