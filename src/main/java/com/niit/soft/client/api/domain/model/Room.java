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
 * @ClassName Room
 * @Description TODO
 * @date 2020-05-25 21:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Room {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    /**
     * 房间名
     */
    @Column(nullable = false)
    private String name;

    /**
     * 楼栋id
     */
    @Column(nullable = false, unique = true)
    private Long towerId;
    /**
     * 单元名
     */
    @Column(name = "unit_id", nullable = false)
    private Long unitId;
    /**
     * 电费余额
     */
    private Double electricityBalance;
    /**
     * 寝室长学号
     */
    @Column(nullable = false, length = 20, unique = true)
    private String roomLeaderJobNumber;

    /**
     * 宿舍成员学号（逗号隔开，用户学号）
     */
    @Column(nullable = false, unique = true)
    private String roomMemberJobNumber;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志
     */
    @Column(nullable = false)
    private Boolean isDeleted;

}
