package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.dto.BorrowDto;
import com.niit.soft.client.api.domain.model.SysBorrow;
import com.niit.soft.client.api.repository.SysBorrowRepository;
import com.niit.soft.client.api.service.SysBorrowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysBorrowServiceImpl
 * @Description TODO
 * @date 2020-06-02 9:30
 **/
@Service
public class SysBorrowServiceImpl implements SysBorrowService {
    @Resource
    private SysBorrowRepository sysBorrowRepository;

    @Override
    public ResponseResult findBorrowMessage(String borrowUserNumber) {

        BorrowDto borrowDto = new BorrowDto();
        List<SysBorrow> sysBorrowsReturn = sysBorrowRepository.findReturnSysBorrows(borrowUserNumber);
        List<SysBorrow> sysBorrowsNoReturn = sysBorrowRepository.findNoReturnSysBorrows(borrowUserNumber);
        int borrowCount = sysBorrowsNoReturn.size() + sysBorrowsNoReturn.size();
        borrowDto.setSysBorrowReturnList(sysBorrowsReturn);
        borrowDto.setSysBorrowNoReturnList(sysBorrowsNoReturn);
        borrowDto.setBorrowCount(borrowCount);
        return ResponseResult.success(borrowDto);
    }
}
