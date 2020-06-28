package com.niit.soft.client.api.service.impl;

import com.alipay.api.domain.AddressDTO;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.dto.QueryDto;
import com.niit.soft.client.api.domain.dto.UpdateUserAccountDto;
import com.niit.soft.client.api.domain.vo.AddressBookVo;
import com.niit.soft.client.api.mapper.UserAccountMapper;
import com.niit.soft.client.api.repository.UserAccountRepository;
import com.niit.soft.client.api.domain.model.UserAccount;
import com.niit.soft.client.api.service.UserAccountService;
import com.niit.soft.client.api.util.Md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/26 10:22
 * @Version 1.0
 **/
@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Resource
    private UserAccountRepository userAccountRepository;
    @Resource
    private UserAccountMapper userAccountMapper;

    @Override
    public UserAccount findUserAccountById(String id) {
        return userAccountRepository.findUserAccountByInfo(id);
    }

    @Override
    public UserAccount findUserAccountByInfo(String info) {
        return userAccountRepository.findUserAccountByInfo(info);
    }

    @Override
    public int updatePasswordByUserAccount(String userAccount, String password) {
        return userAccountRepository.updatePasswordByUserAccount(userAccount, Md5Util.getMd5(password, true, 32));
    }

    @Override
    public ResponseResult findUserAccountLike(String keyword) {
        List<AddressBookVo> addressBookVos = null;
        try {
            addressBookVos = userAccountMapper.findUserAccountLike(keyword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseResult.success(addressBookVos);
    }


    @Override
    public ResponseResult updateUserInfo(UpdateUserAccountDto updateUserAccountDto) {
        UserAccount userAccount = userAccountRepository.findSysUserAccountByPkUserAccountId(updateUserAccountDto.getPkUserAccountId());
        if (userAccount != null) {
            userAccountRepository.updateUserAccount(updateUserAccountDto);
            return ResponseResult.success("修改成功");
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }

    }

    @Override
    public ResponseResult updateUserPhoneNumber(UserAccount userAccount) {
        UserAccount updateSysUserAccount = userAccountRepository.findUserAccountByInfo(userAccount.getPkUserAccountId().toString());
        if (updateSysUserAccount.getStatus()) {
            updateSysUserAccount.setPhoneNumber(userAccount.getPhoneNumber());
            userAccountRepository.saveAndFlush(updateSysUserAccount);
            return ResponseResult.success(updateSysUserAccount);
        }
        return ResponseResult.failure(ResultCode.USER_ACCOUNT_FORBIDDEN);
    }

}
