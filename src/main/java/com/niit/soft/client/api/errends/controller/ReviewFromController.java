package com.niit.soft.client.api.errends.controller;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.errends.domain.dto.FinshOrderDto;
import com.niit.soft.client.api.errends.domain.dto.ReviewFormDto;
import com.niit.soft.client.api.errends.domain.dto.TransactionDto;
import com.niit.soft.client.api.errends.service.ReviewFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wl
 * @ClassNameReviewFromController
 * @Description TODO
 * @Date 2020/6/12
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "跑腿接口")
@RequestMapping("/reviewfrom")
public class ReviewFromController {
    @Resource
    private ReviewFormService reviewFormService;

    @ApiOperation(value = "申请成为跑腿 ", notes = "请求参数为review dto")
    @PostMapping("/application")
    public ResponseResult insertDeliveryOrder(@RequestBody ReviewFormDto reviewFormDto) {
        return reviewFormService.insertReview(reviewFormDto);
    }

    @ApiOperation(value = "判断是否是跑腿 ",notes = "请求参数为transactionDto")
    @PostMapping("/isErrends")
    public ResponseResult isErrends(@RequestBody FinshOrderDto finshOrderDto){
        return reviewFormService.selectErrends(finshOrderDto);
    }



    @ApiOperation(value = "判断是否是状态 ",notes = "请求参数为transactionDto")
    @PostMapping("/status")
    public ResponseResult ErrendsStatus(@RequestBody FinshOrderDto finshOrderDto){
        return reviewFormService.selcetStatus(finshOrderDto);
    }
}
