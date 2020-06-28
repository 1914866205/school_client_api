package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.model.SysFeedback;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysFeedbackService
 * @Description TODO
 * @date 2020-05-26 18:37
 **/
public interface SysFeedbackService {
    /**
     * 新增反馈信息
     *
     * @param sysFeedback
     * @return
     */
    ResponseResult insertSysFeedback(SysFeedback sysFeedback);
}
