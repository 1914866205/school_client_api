package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @ClassName sys_card
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/25
 **/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysCard {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_card_id", nullable = false)
    private Long pkCardId;

    /**
     * 卡号
     */
    @Column(nullable = false, length = 32)
    private String cardNumber;

    /**
     * 卡密
     */
    @Column(nullable = false, length = 32)
    private String cardPassword;

    /**
     * 绑定账号
     */
    @Column(nullable = false, length = 32)
    private String jobNumber;

    /**
     * 余额
     */
    @Column(nullable = false)
    private Double cardBalance;

    /**
     * 状态
     */
    @Column(nullable = false, length = 4)
    private Boolean status;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp gmtModified;

    /**
     * 删除标志
     */
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;


}