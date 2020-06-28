package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * @Description 角色
 * @Author 涛涛
 * @Date 2020/5/25 22:13
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SysRole {
    //角色id
    @NotNull(message = "pkRoleId不能为空")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkRoleId;

    //角色名
    @NotNull(message = "roleName不能为空")
    @Size(min = 1, max = 32)
    @Column(length = 32, nullable = false)
    private String roleName;

    //角色描述
    @NotNull(message = "roleDecoration不能为空")
    @Size(min = 1, max = 32)
    @Column(length = 32, nullable = false)
    private String roleDecoration;

    //排序字段
    @NotNull(message = "sort不能为空")
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
