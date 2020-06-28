
package com.niit.soft.client.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niit.soft.client.api.domain.dto.JobPageDto;
import com.niit.soft.client.api.domain.model.Job;
import com.niit.soft.client.api.domain.vo.JobVo;
import com.niit.soft.client.api.mapper.JobMapper;
import com.niit.soft.client.api.service.JobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author su
 * @className JobService
 * @Description TODO
 * @Date 2020/6/11
 * @Version 1.0
 **/
@Service
//@CacheConfig(cacheNames = {""})
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Resource
    private JobMapper jobMapper;

    @Override
//    @Cacheable(cacheNames = { "jobList" }, key = "#jobPageDto+2")
    public List<JobVo> find(JobPageDto jobPageDto) {
        jobPageDto.setCurrentPage((jobPageDto.getCurrentPage()-1)*jobPageDto.getPageSize());
        return jobMapper.jobList(jobPageDto);
    }

    @Override
//    @Cacheable(cacheNames = { "jobDetail" }, key = "#id")
    public JobVo findById(Long id) {
        return jobMapper.findJob(id);
    }

    @Override
//    @Cacheable(cacheNames = { "jobListByType" }, key = "#jobPageDto")
    public List<JobVo> findByType(JobPageDto jobPageDto) {
        jobPageDto.setCurrentPage((jobPageDto.getCurrentPage()-1)*jobPageDto.getPageSize());
        return jobMapper.jobListByType(jobPageDto);
    }

    @Override
//    @Cacheable(cacheNames = { "jobListByKeyword" }, key = "#jobPageDto")
    public List<JobVo> findByKeyword(JobPageDto jobPageDto) {
        jobPageDto.setCurrentPage((jobPageDto.getCurrentPage()-1)*jobPageDto.getPageSize());
        return jobMapper.jobListByKeyword(jobPageDto);
    }
}

