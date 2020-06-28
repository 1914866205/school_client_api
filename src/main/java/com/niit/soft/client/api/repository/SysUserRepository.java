package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/26 8:23
 * @Version 1.0
 **/

public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    /**
     * 根据id查询用户信息
     *
     * @param userId
     * @return
     */
    SysUser getSysUserByPkUserId(String userId);
}
