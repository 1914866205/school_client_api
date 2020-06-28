package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.JobDto;
import com.niit.soft.client.api.domain.dto.JobPageDto;
import com.niit.soft.client.api.service.JobService;
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
 * @className JobController
 * @Description TODO
 * @Date 2020/6/11
 * @Version 1.0
 **/


@Slf4j
@RestController
@RequestMapping("/job")
@Api(tags = "校园聘的接口")
public class JobController {


    @Resource
    private JobService jobService;

    @PostMapping("/byId")
    @ApiOperation(value = "根据职位id查看详情", notes = "请求参数为id")
    public ResponseResult findJobById(@RequestBody JobDto jobDto) {
        return ResponseResult.success(jobService.findById(jobDto.getId()));
    }

    @PostMapping("/list")
    @ApiOperation(value = "校招职位列表", notes = "请求参数为JobPageDto,field参数为max或gmt_create")
    public ResponseResult findJob(@RequestBody JobPageDto jobPageDto) {
        return ResponseResult.success(jobService.find(jobPageDto));
    }

    @PostMapping("/listByType")
    @ApiOperation(value = "根据类型查看职位", notes = "请求参数为JobPageDto,field参数职位类型id")
    public ResponseResult findJobByType(@RequestBody JobPageDto jobPageDto) {

        return ResponseResult.success(jobService.findByType(jobPageDto));
    }


    @PostMapping("/keyword")
    @ApiOperation(value = "校招模糊查询", notes = "请求参数为JobPageDto,field参数为关键字")
    public ResponseResult findJobByKeyword(@RequestBody JobPageDto jobPageDto) {
        return ResponseResult.success(jobService.findByKeyword(jobPageDto));
    }


}
