
package com.niit.soft.client.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niit.soft.client.api.domain.dto.JobPageDto;
import com.niit.soft.client.api.domain.model.PartJob;
import com.niit.soft.client.api.mapper.PartJobMapper;
import com.niit.soft.client.api.service.PartJobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author su
 * @className JobServiceImpl
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 **/
@Service
//@CacheConfig(cacheNames = {""})
public class PartJobServiceImpl extends ServiceImpl<PartJobMapper, PartJob> implements PartJobService {

    @Resource
    private PartJobMapper partJobMapper;


    @Override
//    @Cacheable(cacheNames = { "partTimeJobList" }, key = "#jobPageDto+1")
    public List<PartJob> findByPage(JobPageDto jobPageDto) {
        QueryWrapper<PartJob> wrapper = new QueryWrapper<>();
        IPage<PartJob> page = new Page<>(jobPageDto.getCurrentPage(), jobPageDto.getPageSize());
        wrapper.select("pk_part_job_id","name","boss_name","boss_phone","boss_avatar","workplace","working_date","working_time","pay","job_type","number","have","need","gmt_create").eq("is_deleted", 0);
        if ("pay".equals(jobPageDto.getField())){
            wrapper.orderByDesc(jobPageDto.getField().toString());
        }else if ("gmt_create".equals(jobPageDto.getField())){
            wrapper.orderByDesc(jobPageDto.getField().toString());
        }else {
            wrapper.eq("job_type", jobPageDto.getField()).orderByDesc("pay");
        }
        return partJobMapper.selectPage(page, wrapper).getRecords();
    }

    @Override
//    @Cacheable(cacheNames = { "partTimeJobDetail" }, key = "#id")
    public PartJob findById(Long id) {
        QueryWrapper<PartJob> wrapper = new QueryWrapper<>();
        wrapper.select("pk_part_job_id","name","description","boss_id","boss_name","boss_phone","boss_avatar","workplace","working_date","working_time","pay","pay_type","job_type","number","have","need","gmt_create").eq("is_deleted", 0).eq("pk_part_job_id",id);
        return partJobMapper.selectOne(wrapper);

    }

    @Override
    public int insertJob(PartJob partJob) {
        partJob.setIsDeleted(false);
        partJob.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
        partJob.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
        return partJobMapper.insert(partJob);
    }

    @Override
//    @Cacheable(cacheNames = { "partTimeJobKeyword" }, key = "#jobPageDto")
    public List<PartJob> findByKeyword(JobPageDto jobPageDto) {
        QueryWrapper<PartJob> wrapper = new QueryWrapper<>();
        IPage<PartJob> page = new Page<>(jobPageDto.getCurrentPage(), jobPageDto.getPageSize());
        wrapper.select("pk_part_job_id","name","boss_name","boss_phone","boss_avatar","workplace","working_date","working_time","pay","job_type","number","have","need","gmt_create").eq("is_deleted", 0).like("name", jobPageDto.getField()).orderByDesc("pay");
        return partJobMapper.selectPage(page,wrapper).getRecords();
    }


}

