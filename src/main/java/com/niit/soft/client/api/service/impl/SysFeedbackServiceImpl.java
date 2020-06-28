package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.model.SysFeedback;
import com.niit.soft.client.api.repository.SysFeedbackRepository;
import com.niit.soft.client.api.service.SysFeedbackService;
import com.niit.soft.client.api.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysFeedbackServiceImpl
 * @Description TODO
 * @date 2020-05-26 18:37
 **/
@Slf4j
@Service
public class SysFeedbackServiceImpl implements SysFeedbackService {
    @Resource
    private SysFeedbackRepository sysFeedbackRepository;

    @Override
    public ResponseResult insertSysFeedback(SysFeedback sysFeedback) {
        boolean title = sysFeedback.getTitle() != null;
        boolean content = sysFeedback.getContent() != null;
        boolean phone = sysFeedback.getContactWay().length() > 0;
        System.out.println(title + "," + content + "," + phone);
        // 若此用户没有匿名 先判断是否有数据
        if (title && content && phone) {
            //数 据均存在 判断手机号码是否正确
            if (StringUtil.isMobile(sysFeedback.getContactWay())) {
                // 存入手机号 标题 内容数据
                SysFeedback sysFeedback1 = SysFeedback.builder()
                        .title(sysFeedback.getTitle())
                        .content(sysFeedback.getContent())
                        .contactWay(sysFeedback.getContactWay())
                        .picInfo(sysFeedback.getPicInfo())
                        .isHandled(false)
                        .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                        .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                        .isDeleted(false)
                        .build();
                // 新增
                sysFeedbackRepository.save(sysFeedback1);
                return ResponseResult.success("新增反馈成功");
            } else {
                // 返回手机号码有误信息
                return ResponseResult.failure(ResultCode.Phone_ERROR);
            }

        } else if (!phone && content && title) {
            // 存入手机号 标题 内容数据
            SysFeedback sysFeedback1 = SysFeedback.builder()
                    .title(sysFeedback.getTitle())
                    .content(sysFeedback.getContent())
                    .picInfo(sysFeedback.getPicInfo())
                    .isHandled(false)
                    .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                    .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                    .isDeleted(false)
                    .build();
            // 新增
            sysFeedbackRepository.save(sysFeedback1);
            return ResponseResult.success("新增反馈成功");
        }
        //返回参数不足
        return ResponseResult.failure(ResultCode.PARAM_NOT_COMPLETE);
    }
}
