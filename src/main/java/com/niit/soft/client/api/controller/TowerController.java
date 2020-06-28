package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.service.TowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Tao
 * @version 1.0
 * @ClassName TowerController
 * @Description TODO
 * @date 2020-06-10 8:34
 **/
@RestController
@RequestMapping("/tower")
@Api(value = "TowerController", tags = {"楼栋模块接口"})
public class TowerController {
    @Resource
    private TowerService towerService;

    @ApiOperation(value = "查询所有宿舍数据接口", notes = "")
    @PostMapping(value = "/all")
    ResponseResult findAllDormByType() {
        return towerService.getAllDormByType();
    }
}
