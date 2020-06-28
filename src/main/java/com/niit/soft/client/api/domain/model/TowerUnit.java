package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @ClassName TowerUnit
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/9
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class TowerUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id", nullable = false)
    private Long unitId;
    /**
     * 单元名
     */
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    /**
     * 创建时间
     */
    @Column(name = "gmt_create", nullable = false)
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @UpdateTimestamp
    @Column(name = "gmt_modified", nullable = false)
    private Timestamp gmtModified;
    /**
     * 删除标志
     */
    @Column(name = "is_deleted", nullable = false, length = 4)
    private Boolean isDeleted;
}