package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.annotation.ControllerWebLog;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Tao
 * @version 1.0
 * @ClassName MessageController
 * @Description 消息控制器
 * @date 2020-05-26 16:03
 **/
@RestController
@Slf4j
@RequestMapping("/message")
@Api(value = "MessageController", tags = {"消息接口"})
public class MessageController {
    @Resource
    private MessageService messageService;

    /**
     * 查询所有消息
     *
     * @param pageDto
     * @return
     */
    @ApiOperation(value = "查询所有", notes = "请求参数为当前页和页面条数")
    @PostMapping("/all")
    @ControllerWebLog(name = "findAllByPage", isSaved = true)
    ResponseResult findAllByPage(@RequestBody PageDto pageDto) {
        return messageService.findAllByPage(pageDto);
    }


}
