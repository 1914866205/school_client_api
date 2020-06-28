
package com.niit.soft.client.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niit.soft.client.api.domain.dto.JobPageDto;
import com.niit.soft.client.api.domain.model.Company;
import com.niit.soft.client.api.domain.vo.CompanyVo;
import com.niit.soft.client.api.mapper.CompanyMapper;
import com.niit.soft.client.api.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author su
 * @className CompanyServiceImpl
 * @Description TODO
 * @Date 2020/6/11
 * @Version 1.0
 **/
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;

    @Override
    public List<Company> findByPage(JobPageDto jobPageDto) {
        QueryWrapper<Company> wrapper = new QueryWrapper<>();

        if ("workers".equals(jobPageDto.getField())){
            wrapper.select("pk_company_id","name","tag","logo","workers","type","address").eq("is_deleted", 0).orderByDesc("workers");
        }else {
            wrapper.select("pk_company_id","name","tag","logo","workers","type","address").eq("is_deleted", 0).like("name",jobPageDto.getField().toString()).orderByDesc("workers");
        }
        IPage<Company> page = new Page<>(jobPageDto.getCurrentPage(), jobPageDto.getPageSize());
        return companyMapper.selectPage(page, wrapper).getRecords();
    }

    @Override
    public CompanyVo findById(Long id) {
        return companyMapper.findDetails(id);
    }

}

