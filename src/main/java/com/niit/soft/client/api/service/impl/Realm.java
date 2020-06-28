package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.domain.model.UserAccount;
import com.niit.soft.client.api.domain.vo.JwtToken;
import com.niit.soft.client.api.service.UserAccountService;
import com.niit.soft.client.api.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/24 9:36
 * @Version 1.0
 **/
@Component
@Slf4j
public class Realm extends AuthorizingRealm {
    @Resource
    private UserAccountService userAccountService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 当有只需要用户权限的时候才会调用此方法
     * 鉴于本例，只需要认证即可，无需鉴权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正常与是否验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        log.info("当前token{}--------------", token);
        //解密获得username,用于和数据库进行对比
        String userAccount = JwtUtil.getUserAccount(token);
        if (userAccount == null) {
            log.error("token无效(空''或者null都不行!)");

            throw new AuthenticationException("token无效");
        }
        UserAccount user = userAccountService.findUserAccountByInfo(userAccount);
        if (user == null) {
            log.error("用户不存在");
            throw new AuthenticationException("用户不存在");
        }

        if (!JwtUtil.verify(token, userAccount, user.getPassword())) {
            log.error("用户名或密码错误(token无效)");
            throw new AuthenticationException("用户名或密码错误(token无效)");
        }

        return new SimpleAuthenticationInfo(token, token, "realm");
    }
}
