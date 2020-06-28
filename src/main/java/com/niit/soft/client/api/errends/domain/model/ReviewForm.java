package com.niit.soft.client.api.errends.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author wl
 * @ClassNameReviewForm
 * @Description 申请表
 * @Date 2020/6/12
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "review_form")
public class ReviewForm {
    /**
     * 主键
     */
    @Id
    @Column(name = "id" ,length = 30)
    private String id;
    /**
     * 申请人id
     */
    @Column(name = "requester_id")
    private String requesterId;
    /**
     * 申请人正面身份证
     */
    @Column(name = "id_card_front")
    private String idCardFront;
    /**
     * 申请人背面身份证
     */
    @Column(name = "id_card_back")
    private String idCardBack;
    /**
     * 申请人手机号
     */
    @Column(name = "requester_phonenumber")
    private String requesterPhonenumber;
    /**
     * 申请人手机号
     */
    @Column(name = "requester_name")
    private String requesterName;
    /**
     * 状态 0是发布  1是通过 2是未通过
     */
    @Column(name = "status")
    private Long status;
    /**
     * 申请原因
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 创建时间
     */
    @JsonIgnore
    @Column(nullable = false)
    @CreatedDate
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @JsonIgnore
    @LastModifiedDate
    @Column(nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
//    @JsonIgnore
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;

}
