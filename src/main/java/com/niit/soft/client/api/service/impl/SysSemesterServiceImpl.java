package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.repository.SysSemesterRepository;
import com.niit.soft.client.api.service.SysSemesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysSemesterServiceImpl
 * @Description TODO
 * @date 2020-05-30 0:29
 **/
@Slf4j
@Service
public class SysSemesterServiceImpl implements SysSemesterService {
    @Resource
    private SysSemesterRepository sysSemesterRepository;

    @Override
    public ResponseResult findAllSysSemester() {
        return ResponseResult.success(sysSemesterRepository.findAllSysSemester());
    }
}
