package com.niit.soft.client.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niit.soft.client.api.domain.dto.JobPageDto;
import com.niit.soft.client.api.domain.model.Job;
import com.niit.soft.client.api.domain.vo.JobVo;

import java.util.List;

/**
 * @author Su
 * @className JobMapper
 * @Description TODO
 * @Date 2020/6/11 15:44
 * @Version 1.0
 **/
public interface JobMapper extends BaseMapper<Job> {

    /**
     * 职位详情
     *
     * @param id
     * @return
     */
    JobVo findJob(Long id);


    /**
     * 职位列表
     *
     * @param jobPageDto
     * @return
     */
    List<JobVo> jobList(JobPageDto jobPageDto);

    /**
<<<<<<< HEAD
     * 根据类id得职位列表
     *
=======
     * 根据类型id得职位列表
>>>>>>> 37f4135f4c2278a10ed530f34ee9a4aafc039001
     * @param jobPageDto
     * @return
     */
    List<JobVo> jobListByType(JobPageDto jobPageDto);


    /**
     * 根据关键字模糊查询
     *
     * @param jobPageDto
     * @return
     */
    List<JobVo> jobListByKeyword(JobPageDto jobPageDto);

}
