package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.SingleFieldDto;
import com.niit.soft.client.api.domain.model.Examination;
import com.niit.soft.client.api.service.ExaminationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/9
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "examination")
@Api(tags = "考务管理")
public class ExaminationController {
    @Resource
    private ExaminationService examinationService;

    @ApiOperation("根据学期分类查询所有接口")
    @PostMapping(value = "list/semester")
    public ResponseResult getExaminationBySemester(@RequestBody SingleFieldDto singleFieldDto) {
        return ResponseResult.success(examinationService.getExaminationBySemester(singleFieldDto.getField().toString()));
    }
}
