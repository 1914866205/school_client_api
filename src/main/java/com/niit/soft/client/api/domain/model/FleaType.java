package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
public class FleaType {
    /**
     * 分类id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkFleaTypeId;

    /**
     * 父级id
     */
    @Column(nullable = false, length = 11)
    private Long parentId;

    /**
     * 分类名称
     */
    @Column(nullable = false, length = 32, unique = true)
    private String typeName;


    /**
     * 分类封面
     */
    @Column(nullable = false)
    private String typeCoverUrl;

    /**
     * 路径
     */
    @Column(nullable = false)
    private String typeUrl;

    /**
     * 删除标志
     */
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;

}
