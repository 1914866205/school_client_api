package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @Description 角色权限关联表
 * @Author 涛涛
 * @Date 2020/5/25 22:20
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SysRoleMenu {
    //主键
    @NotNull(message = "pkRoleMenuId不能为空")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkRoleMenuId;

    //角色id
    @NotNull(message = "pkRoleId不能为空")
    @Column(unique = true)
    private Long pkRoleId;

    //权限id
    @NotNull(message = "menuId不能为空")
    @Column(unique = true)
    private Long menuId;

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
