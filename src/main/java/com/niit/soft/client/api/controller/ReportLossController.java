package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.dto.SecondFieldDto;
import com.niit.soft.client.api.domain.model.ReportLoss;
import com.niit.soft.client.api.service.ReportLossService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ReportLossController
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/1
 **/
@Slf4j
@RestController
@Api(value = "ReportLossController", tags = {"挂失接口"})
public class ReportLossController {
    @Resource
    private ReportLossService reportLossService;

    /**
     * 分页查询所有消息
     *
     * @param pageDto
     * @return
     */
    @PostMapping("/loss/all")
    ResponseResult findAllByPage(@RequestBody PageDto pageDto) {
        return reportLossService.findAllByPage(pageDto);
    }

    /**
     * @param secondFieldDto
     * @return
     */
    @PostMapping("/loss/statuschange")
    ResponseResult updateLossStatus(@RequestBody SecondFieldDto secondFieldDto) {
        boolean lossStatus = Boolean.parseBoolean(secondFieldDto.getField2().toString());
        return reportLossService.updateLossStatus(Long.parseLong(String.valueOf(secondFieldDto.getField1())), lossStatus);
    }

    /**
     * 申请挂失
     *
     * @param reportLoss
     * @return
     */
    @ApiOperation(value = "申请挂失信息", notes = "")
    @PostMapping(value = "/increase")
    public ResponseResult increaseReportLoss(@RequestBody ReportLoss reportLoss) {
        return reportLossService.insertReportLoss(reportLoss);
    }

    /**
     * 取消挂失
     *
     * @param reportLoss
     * @return
     */
    @ApiOperation(value = "取消挂失信息", notes = "")
    @PostMapping(value = "/deletion")
    public ResponseResult deletionReportLoss(@RequestBody ReportLoss reportLoss) {
        return reportLossService.cancelReportLoss(reportLoss);
    }

}