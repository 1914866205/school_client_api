package com.niit.soft.client.api.errends.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.errends.domain.dto.FinshOrderDto;
import com.niit.soft.client.api.errends.domain.dto.ReviewFormDto;

/**
 * @author wl
 * @ClassNameReviewFormService
 * @Description 申请成为跑腿申请表
 * @Date 2020/6/12
 * @Version 1.0
 */
public interface ReviewFormService {

    /**
     * 申请成为跑腿
     *
     * @return
     */
    ResponseResult insertReview(ReviewFormDto reviewFormDto);

    /**
     * 查询是否为跑腿
     *
     * @param finshOrderDto
     * @return
     */
    ResponseResult selectErrends(FinshOrderDto finshOrderDto);

    /**
     * 查询跑腿申请状态
     */
    ResponseResult selcetStatus(FinshOrderDto finshOrderDto);

}
