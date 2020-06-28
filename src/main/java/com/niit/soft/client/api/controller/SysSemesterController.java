package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.service.SysSemesterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysSemesterController
 * @Description TODO
 * @date 2020-05-30 0:32
 **/
@RestController
@RequestMapping("/semester")
@Api(value = "SysSemesterController", tags = {"学期模块接口"})
public class SysSemesterController {
    @Resource
    private SysSemesterService sysSemesterService;

    @ApiOperation(value = "查询所有学期接口", notes = "")
    @PostMapping(value = "/all")
    ResponseResult findAllSysSemester() {
        return sysSemesterService.findAllSysSemester();
    }
}
