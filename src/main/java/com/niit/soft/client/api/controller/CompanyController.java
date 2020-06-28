package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.JobDto;
import com.niit.soft.client.api.domain.dto.JobPageDto;
import com.niit.soft.client.api.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author su
 * @className CompanyController
 * @Description TODO
 * @Date 2020/6/12
 * @Version 1.0
 **/

@Slf4j
@RestController
@RequestMapping("/company")
@Api(tags = "校园聘的接口")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    @PostMapping("/list")
    @ApiOperation(value = "公司列表", notes = "JobPageDto,field参数为workers")
    public ResponseResult findCompany(@RequestBody JobPageDto jobPageDto) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>" + jobPageDto);
        return ResponseResult.success(companyService.findByPage(jobPageDto));
    }

    @PostMapping("/detail")
    @ApiOperation(value = "公司详情", notes = "请求参数为公司id")
    public ResponseResult companyDetail(@RequestBody JobDto jobDto) {
        return ResponseResult.success(companyService.findById(jobDto.getId()));
    }
}
