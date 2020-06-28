package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @Description 资源权限表
 * @Author 涛涛
 * @Date 2020/5/25 21:29
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SysMenu {
    //主键
    @NotNull(message = "pkMenuId不能为空")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkMenuId;

    //父类id
    @NotNull(message = "parentId不能为空")
    @Column(nullable = false, unique = true)
    private Long parentId;

    //类型（0 目录，1 菜单，2 按钮）
    @NotNull(message = "type不能为空")
    @Column(nullable = false, length = 4, unique = true)
    private Integer type;

    //名称
    @NotNull(message = "name不能为空")
    @Column(length = 60, nullable = false)
    private String name;
    //路径
//    @Column(length = 255, nullable = true)
    @Column()
    private String path;
    //图标
    @Column()
    private String icon;
    //排序
    @Column(length = 4, nullable = false)
    private Integer sort;

    //创建时间
    @NotNull(message = "gmtCreate不能为空")
    @Column(nullable = false)
    private Timestamp gmtCreate;

    //修改时间
    @NotNull(message = "gmtModified不能为空")
    @Column(nullable = false)
    private Timestamp gmtModified;

    //是否删除（1 逻辑删除， 0 未删除）
    @NotNull(message = "isDeleted不能为空")
    @Column(length = 4, nullable = false)
    private Boolean isDeleted;
}
