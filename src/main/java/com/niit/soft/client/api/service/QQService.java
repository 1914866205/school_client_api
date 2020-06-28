package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName QQService.java
 * @Description TODO
 * @createTime 2020年06月17日 21:23:00
 */
public interface QQService {
    /**
     * 用户请求第三方登录，重定向到QQ互联
     *
     * @param session
     * @return
     */
    String qqRedirect(HttpSession session);

    /**
     * QQ回调，QQ互联返回openid,该id可以唯一锁定用户
     *
     * @param request
     * @return
     */
    String connect(HttpServletRequest request) throws IOException;
}
