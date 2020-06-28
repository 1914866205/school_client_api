package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.annotation.ControllerWebLog;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.JobDto;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.dto.SingleFieldDto;
import com.niit.soft.client.api.service.InfoManageService;
import com.niit.soft.client.api.service.InfoTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Yujie_Zhao
 * @ClassName InfoManageController
 * @Description 资讯模块Controller层
 * @Date 2020/5/26  11:52
 * @Version 1.0
 **/

@RestController
@Slf4j
@RequestMapping(value = "/info")
@Api(value = "InfoManageController", tags = {"资讯接口"})
public class InfoManageController {
    @Resource
    private InfoManageService infoManageService;

    @Resource
    private InfoTypeService infoTypeService;


    /**
     * 分页查找所有资讯
     *
     * @return List<InfoManage>
     */
    @ControllerWebLog(name = "findAllInfoByPage", isSaved = true)
    @ApiOperation(value = "分页查找所有资讯", notes = "请求参数为当前页和页面条数，不包含置顶咨询，从第0页开始")
    @PostMapping(value = "/allInfo")
    public ResponseResult findAllInfoByPage(@RequestBody PageDto pageDto) {
        return infoManageService.findAllInfoByPage(pageDto);
    }

    /**
     * 查询置顶资讯
     *
     * @return List<InfoManage>
     */
    @ControllerWebLog(name = "getIsTopInfo", isSaved = true)
    @ApiOperation(value = "分页查询置顶资讯", notes = "请求参数为当前页和页面条数，从第0页开始,")
    @PostMapping(value = "/isTap")
    public ResponseResult getIsTopInfo(@RequestBody PageDto pageDto) {
        return infoManageService.getIsTopInfo(pageDto);
    }

    /**
     * 查询所有的资讯分类
     *
     * @return InfoType
     */
    @ControllerWebLog(name = "getInfoAllType", isSaved = true)
    @ApiOperation(value = "查询所有的资讯分类", notes = "")
    @PostMapping(value = "/type/all")
    public ResponseResult getInfoAllType() {
        return infoTypeService.getAllType();
    }


    @ControllerWebLog(name = "findInfoByTypeId", isSaved = true)
    @ApiOperation(value = "通过资讯分类id查询相对应的资讯数据", notes = "请求参数为资讯的分类id,当前页，每页条数")
    @PostMapping(value = "/type/page")
    public ResponseResult findInfoByTypeId(@RequestBody PageDto pageDto) {
        log.info("-----/typeId-----请求参数：" + pageDto + "**1**");
        return infoTypeService.findInfoByTypeId(pageDto);
    }

    @ApiOperation(value = "根据咨询id查询详情数据", notes = "")
    @PostMapping(value = "/single")
    public ResponseResult findInfoManageById(@RequestBody JobDto jobDto) {
        return infoManageService.findInfoManageById(jobDto.getId());
    }
}
