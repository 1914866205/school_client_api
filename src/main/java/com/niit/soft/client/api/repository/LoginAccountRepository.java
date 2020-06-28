package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.LoginAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.management.GarbageCollectorMXBean;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName LoginAccountRepository.java
 * @Description TODO
 * @createTime 2020年06月17日 21:44:00
 */
public interface LoginAccountRepository extends JpaRepository<LoginAccount, Long> {
    /**
     * 根据qqOpenId查询
     *
     * @param qqOpenId
     * @return
     */
    LoginAccount getLoginAccountByQqOpenIdEquals(String qqOpenId);


    LoginAccount getLoginAccountByJobNumberEquals(String jobNumber);

    boolean existsLoginAccountByJobNumberEquals(String jobNumber);

    boolean existsLoginAccountByQqOpenIdEquals(String openid);

}
