package com.niit.soft.client.api.errends.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wl
 * @ClassNameReviewFormDto
 * @Description TODO
 * @Date 2020/6/12
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewFormDto {

    /**
     * 申请人id
     */

    private String requesterId;
    /**
     * 申请人正面身份证
     */

    private String idCardFront;
    /**
     * 申请人背面身份证
     */

    private String idCardBack;
    /**
     * 申请人手机号
     */

    private String requesterPhonenumber;
    /**
     * 申请人手机号
     */

    private String requesterName;

    /**
     * 申请原因
     */

    private String remark;

}
