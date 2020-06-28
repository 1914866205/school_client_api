package com.niit.soft.client.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niit.soft.client.api.domain.dto.JobDto;
import com.niit.soft.client.api.domain.model.Company;
import com.niit.soft.client.api.domain.vo.CompanyVo;

/**
 * @author Su
 * @className CompanyMapper
 * @Description TODO
 * @Date 2020/6/11 17:33
 * @Version 1.0
 **/
public interface CompanyMapper extends BaseMapper<Company> {

    CompanyVo findDetails(Long id);
}
