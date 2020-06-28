package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Description 系统用户表
 * @Author 涛涛
 * @Date 2020/5/25 21:58
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SysUser {
    //用户Id
    @NotNull(message = "pkUserId不能为空")
    @Size(min = 1, max = 32)
    @Id
    @Column(length = 32)
    private String pkUserId;

    //用户名字
    @NotNull(message = "sysUserName不能为空")
    @Size(min = 1, max = 60)
    @Column(length = 60, nullable = false)
    private String sysUserName;

    //用户密码
    @NotNull(message = "sysPassword不能为空")
    @Size(min = 1, max = 32)
    @Column(length = 32, nullable = false)
    private String sysPassword;

    //手机号
    @Pattern(regexp = "^[1](([3|5|8][\\\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\\\d]{8}$", message = "手机号码格式错误")
    @NotBlank(message = "手机号码不能为空")
    @Column(length = 30, unique = true, nullable = false)
    private String sysUserPhoneNumber;

    //头像
    @NotNull(message = "sysUserAvatar不能为空")
    @Column(nullable = false)
    private String sysUserAvatar;

    //加密盐
    @NotNull(message = "salt不能为空")
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String salt;

    //账户状态 ： 0 禁用   1 ：启用
    @NotNull(message = "isEnabled不能为空")
    @Column(length = 4)
    private Boolean isEnabled;

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
