package com.niit.soft.client.api.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaUserVo {
    private Long pkFleaUserId;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 性别
     */
    private String sex;
    /**
     * 学号
     */
    private String jobNumber;
}
