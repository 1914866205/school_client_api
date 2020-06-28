package com.niit.soft.client.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @ClassName ReportLoss
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/25
 **/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportLoss {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkReportLossId;

    /**
     * 挂失备注
     */
    @Column
    private String remark;

    /**
     * 挂失卡密
     */
    @Column(nullable = false, length = 128)
    private String password;

    /**
     * 挂失状态
     */
    @Column(nullable = false, length = 4)
    private Boolean lossStatus;

    /**
     * 手机号
     */
    @Column(nullable = false, unique = true, length = 32)
    private String lossPhone;

    /**
     * 挂失人
     */
    @Column(nullable = false, length = 32)
    private String lossName;

    /**
     * 挂失卡号
     */
    @Column(nullable = false, unique = true, length = 32)
    private String lossJobNumber;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @Column(nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志
     */
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;
}