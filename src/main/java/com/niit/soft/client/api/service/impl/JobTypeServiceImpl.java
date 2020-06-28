
package com.niit.soft.client.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niit.soft.client.api.domain.dto.JobTypeDto;
import com.niit.soft.client.api.domain.model.JobType;
import com.niit.soft.client.api.mapper.JobTypeMapper;
import com.niit.soft.client.api.service.JobTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;



/**
 * @author su
 * @className JobTypeService
 * @Description TODO
 * @Date 2020/6/11
 * @Version 1.0
 **/
@Service
@Slf4j
public class JobTypeServiceImpl extends ServiceImpl<JobTypeMapper, JobType> implements JobTypeService {

    @Resource
    private JobTypeMapper jobTypeMapper;

    Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());


    @Override
    public int addJobType(JobTypeDto jobTypeDto) {
        JobType jobType = JobType.builder()
                .name(jobTypeDto.getName())
                .parentId(jobTypeDto.getParentId())
                .isDeleted(false)
                .gmtCreate(timestamp)
                .gmtModified(timestamp)
                .build();
        return jobTypeMapper.insert(jobType);
    }

    @Override
    public List<JobType> findType() {
        QueryWrapper<JobType> wrapper = new QueryWrapper<>();
        wrapper.select("pk_job_type_id","name").eq("is_deleted", 0);
        return jobTypeMapper.selectList(wrapper);
    }
}

