package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.model.InfoManage;
import com.niit.soft.client.api.domain.model.InfoType;
import com.niit.soft.client.api.repository.InfoManageRepository;
import com.niit.soft.client.api.repository.InfoMangeTypeRepository;
import com.niit.soft.client.api.repository.InfoTypeRepository;
import com.niit.soft.client.api.service.InfoTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName InfoTypeServiceImpl
 * @Description 资讯分类Service实现类
 * @Date 2020/5/26  22:05
 * @Version 1.0
 **/
@Service
public class InfoTypeServiceImpl implements InfoTypeService {

    @Resource
    private InfoTypeRepository infoTypeRepository;
    @Resource
    private InfoMangeTypeRepository infoMangeTypeRepository;
    @Resource
    private InfoManageRepository infoManageRepository;


    @Override
    public ResponseResult getAllType() {
        List<InfoType> infoTypeList = infoTypeRepository.findAll();
        return ResponseResult.success(infoTypeList);
    }


    @Override
    public ResponseResult findInfoByTypeId(PageDto pageDto) {
//        System.out.println(pageDto.getField());
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pkInfoManageId");
        Page<InfoManage> infoManageByPage = infoTypeRepository.findInfoMangeByPage(
                Long.parseLong((String) pageDto.getField()),
                pageable);
        return ResponseResult.success(infoManageByPage.getContent());
    }
}
