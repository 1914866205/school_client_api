package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Yujie_Zhao
 * @ClassName AddressBook
 * @Description 通讯录表
 * @Date 2020/5/25  22:46
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "address_book", indexes = {@Index(name = "phone_numberIndex", columnList = "phone_number")})
public class AddressBook {

    /**
     * 主键，策略为自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkAddressBookId;

    /**
     * 用户id
     */
    @Column(nullable = false, length = 32)
    private String userId;
    /**
     * 性别
     */
    @Column(length = 32)
    private String gender;
    /**
     * 真实名字
     */
    private String realName;

    /**
     * 手机号
     */
    @Column(name = "phone_number", nullable = false, length = 32)
    private String phoneNumber;

    /**
     * 头像
     */
    @Column(nullable = false)
    private String avatar;

    /**
     * 备注
     */
    @Column(nullable = false)
    private String remark;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;

    /*@OneToOne(targetEntity = UserAccount.class)
    @JoinColumn(name = "userPhone", referencedColumnName="phoneNumber")
    private UserAccount userAccount;*/
}
