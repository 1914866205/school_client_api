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
public class FleaUser {

    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkFleaUserId;

    /**
     * 用户昵称
     */
    @Column(nullable = false, length = 32)
    private String nickname;

    /**
     * 用户名
     */
    @Column(nullable = false, length = 32)
    private String username;


    /**
     * 手机号
     */
    @Column(nullable = false, length = 20, unique = true)
    private String phoneNumber;

    /**
     * 用户头像
     */
    @Column(nullable = false)
    private String avatar;

    /**
     * 性别
     */
    @Column(nullable = false, length = 4)
    private String sex;

    /**
     * 学号
     */

    @Column(nullable = false, length = 12)
    private String jobNumber;


    /**
     * 删除标志
     */
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;


}
