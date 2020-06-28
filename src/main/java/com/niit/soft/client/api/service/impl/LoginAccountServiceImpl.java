package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.LoginAccountIncreased;
import com.niit.soft.client.api.domain.model.LoginAccount;
import com.niit.soft.client.api.repository.LoginAccountRepository;
import com.niit.soft.client.api.service.LoginAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName LoginAccountServiceImpl.java
 * @Description TODO
 * @createTime 2020年06月17日 22:59:00
 */
@Service
@Slf4j
public class LoginAccountServiceImpl implements LoginAccountService {
    @Resource
    private LoginAccountRepository loginAccountRepository;

    @Override
    public ResponseResult increasedLoginAccount(LoginAccountIncreased loginAccountIncreased) {
        LoginAccount loginAccount = LoginAccount.builder()
                .jobNumber(loginAccountIncreased.getJobNumber())
                .qqOpenId(loginAccountIncreased.getQqOpenId())
                .build();
        log.info("绑定QQ");
        return ResponseResult.success(loginAccountRepository.save(loginAccount));
    }

    @Override
    public ResponseResult findByJobNumber(String jobNumber) {
        LoginAccount loginAccount = loginAccountRepository.getLoginAccountByJobNumberEquals(jobNumber);
        log.info(loginAccount.toString());
        if (loginAccount == null) {
            return ResponseResult.success("no");
        }else {
            return ResponseResult.success("ok");
        }
    }

    @Override
    public ResponseResult deletedByJobNumber(String jobNumber) {
        LoginAccount loginAccount = loginAccountRepository.getLoginAccountByJobNumberEquals(jobNumber);
        loginAccountRepository.deleteById(loginAccount.getPkLoginAccountId());
        return ResponseResult.success("ok");
    }
}
