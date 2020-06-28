package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.model.InfoManage;
import com.niit.soft.client.api.repository.InfoManageRepository;
import com.niit.soft.client.api.service.InfoManageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Yujie_Zhao
 * @ClassName InfoManageServiceImpl
 * @Description TODO
 * @Date 2020/5/26  11:41
 * @Version 1.0
 **/
@Service
public class InfoManageServiceImpl implements InfoManageService {

    @Resource
    private InfoManageRepository infoManageRepository;


    /**
     * 分页查询全部资讯
     *
     * @param pageDto
     * @return
     */
    @Override
    public ResponseResult findAllInfoByPage(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pk_info_manage_id");
        Page<InfoManage> infoManagePage = infoManageRepository.getAllManage(pageable);
        return ResponseResult.success(infoManagePage.getContent());
    }

    /**
     * 分页查询置顶资讯
     *
     * @return List<InfoManage>
     */
    @Override
    public ResponseResult getIsTopInfo(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pk_info_manage_id");
        Page<InfoManage> infoManagePage = infoManageRepository.getAllManage(pageable);
        return ResponseResult.success(infoManagePage.getContent());
    }

    @Override
    public ResponseResult findInfoManageById(Long id) {
        InfoManage infoManage = infoManageRepository.getInfoManageByPkInfoManageId(id);
        if (infoManage != null) {
            return ResponseResult.success(infoManage);
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

}
