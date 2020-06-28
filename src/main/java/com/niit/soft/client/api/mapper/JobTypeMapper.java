package com.niit.soft.client.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niit.soft.client.api.domain.model.JobType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author Su
 * @className JobType
 * @Description TODO
 * @Date 2020/6/11 9:10
 * @Version 1.0
 **/
@Component
public interface JobTypeMapper extends BaseMapper<JobType> {


    @Select("SELECT name FROM job_type WHERE name = #{name}")
    String selectName(String name);
}
