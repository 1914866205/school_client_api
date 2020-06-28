
package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.QueryDto;
import com.niit.soft.client.api.domain.dto.UpdateUserAccountDto;
import com.niit.soft.client.api.domain.model.UserAccount;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Tao
 */
public interface UserAccountService {
    /**
     * 修改
     *
     * @param updateUserAccountDto
     * @return
     */
    ResponseResult updateUserInfo(UpdateUserAccountDto updateUserAccountDto);


    /**
     * 修改手机号码
     *
     * @param userAccount
     * @return
     */
    ResponseResult updateUserPhoneNumber(UserAccount userAccount);


    /**
     * 根据主键查询用户信息
     *
     * @param id
     * @return
     */
    UserAccount findUserAccountById(String id);

    /**
     * 根据用户唯一信息获取用户对象
     *
     * @param info
     * @return
     */
    UserAccount findUserAccountByInfo(String info);

    /**
     * 根据userAccount或job_number或phoneNumber修改密码
     *
     * @param userAccount
     * @param password
     * @return
     */
    int updatePasswordByUserAccount(String userAccount, String password);


    /**
     * 根据手机号或者用户名模糊查询
     *
     * @param keyword
     * @return
     */
    ResponseResult findUserAccountLike(String keyword);


}
