package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.model.ReportLoss;

/**
 * @ClassName ReportLossService
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/1
 **/
public interface ReportLossService {
    /**
     * 分页查询挂失信息
     *
     * @param pageDto
     * @return
     */
    ResponseResult findAllByPage(PageDto pageDto);

    /**
     * 校园卡挂失
     *
     * @param pkReportLossId
     * @param lossStatus
     * @return
     */
    ResponseResult updateLossStatus(Long pkReportLossId, Boolean lossStatus);


    /**
     * 新增挂失
     *
     * @param reportLoss
     * @return
     */
    ResponseResult insertReportLoss(ReportLoss reportLoss);

    /**
     * 取消挂失
     *
     * @param reportLoss
     * @return
     */
    ResponseResult cancelReportLoss(ReportLoss reportLoss);
}