package com.niit.soft.client.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niit.soft.client.api.domain.dto.JobPageDto;
import com.niit.soft.client.api.domain.model.Job;
import com.niit.soft.client.api.domain.vo.JobVo;

import java.util.List;

/**
 * @author Su
 * @className JobService
 * @Description TODO
 * @Date 2020/6/11 15:45
 * @Version 1.0
 **/
public interface JobService extends IService<Job> {


    List<JobVo> find(JobPageDto jobPageDto);


    JobVo findById(Long id);


    List<JobVo> findByType(JobPageDto jobPageDto);


    List<JobVo> findByKeyword(JobPageDto jobPageDto);

}
