package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.LoginAccountIncreased;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName LoginAccountService.java
 * @Description 目前仅维护QQ的openid和用户的关系
 * @createTime 2020年06月17日 21:45:00
 */
public interface LoginAccountService {

    ResponseResult increasedLoginAccount(LoginAccountIncreased loginAccountIncreased);

    ResponseResult findByJobNumber(String jobNumber);

    ResponseResult deletedByJobNumber(String jobNumber);
}
