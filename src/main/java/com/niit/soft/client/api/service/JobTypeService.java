package com.niit.soft.client.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niit.soft.client.api.domain.dto.JobTypeDto;
import com.niit.soft.client.api.domain.model.Job;
import com.niit.soft.client.api.domain.model.JobType;

import java.util.List;

/**
 * @author Su
 * @className JobType
 * @Description TODO
 * @Date 2020/6/11 9:11
 * @Version 1.0
 **/
public interface JobTypeService extends IService<JobType> {

    int addJobType(JobTypeDto jobTypeDto);

    List<JobType> findType();
}
