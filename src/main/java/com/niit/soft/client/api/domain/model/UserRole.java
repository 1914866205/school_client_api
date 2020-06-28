package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @Description 用户角色关联表
 * @Author 涛涛
 * @Date 2020/5/25 21:47
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserRole {
    //主键
    @NotNull(message = "pkRoleId不能为空")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkRoleId;
    //用户id
    @NotNull(message = "sysUserId不能为空")
    @Column(unique = true)
    private Long sysUserId;

    //角色id
    @NotNull(message = "roleId不能为空")
    @Column(unique = true)
    private Long roleId;

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
