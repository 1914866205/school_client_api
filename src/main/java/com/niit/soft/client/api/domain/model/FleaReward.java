package com.niit.soft.client.api.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName a.java
 * @Description TODO
 * @createTime 2020年06月09日 11:26:00
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaReward {
    /**
     * 悬赏Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkRewardId;

    /**
     * 描述
     */
    @Column(nullable = false)
    private String description;

    /**
     * 图片地址
     */
    @Column(nullable = false)
    private String imageUrl;


    /**
     * 标题
     */
    @Column(nullable = false, length = 50)
    private String title;


    /**
     * 发布时间
     */
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;


    /**
     * 发布人
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private FleaUser fleaUser;
    /**
     * 删除标志
     */
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;
}
