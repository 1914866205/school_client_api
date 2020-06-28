package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.annotation.ControllerWebLog;
import com.niit.soft.client.api.service.QQService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName QQController.java
 * @Description 登录流程：
 * 1.用户在前端点击图标
 * 2.跳转到需要点击的页面
 * 3.点击后进行第三方登录 访问后端接口，重定向到QQ互联
 * https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101883898&redirect_uri=http%3A%2F%2Fwww.ntt1914866205.xyz%2Fconnect&state=040f78c2b71d462f8f3684c348f08cac
 * 4.QQ互联回调，返回部分数据
 * code>>>>>>>>>>A963EFBDF8A569F0A5C76B9273BAE19D
 * state>>>>>>>>>>A963EFBDF8A569F0A5C76B9273BAE19D
 * uuid>>>>>>>>>>040f78c2b71d462f8f3684c348f08cac
 * 5.获取回调后的 openid 值>>>>>>>>https://graph.qq.com/oauth2.0/me?access_token=82EECEA6B22C343C60FB79FBE9C602E5
 * 6.获取QQ用户信息jsonObject{"ret":0,"msg":"","is_lost":0,"gender":"男","is_yellow_vip":"0","city":"南京","year":"1999","level":"0","figureurl_2":"http://qzapp.qlogo.cn/qzapp/101883898/EA01EC237FA0A2FC1D2842D07C638E58/100","figureurl_1":"http://qzapp.qlogo.cn/qzapp/101883898/EA01EC237FA0A2FC1D2842D07C638E58/50","gender_type":1,"is_yellow_year_vip":"0","province":"江苏","constellation":"","figureurl":"http://qzapp.qlogo.cn/qzapp/101883898/EA01EC237FA0A2FC1D2842D07C638E58/30","figureurl_type":"1","figureurl_qq":"http://thirdqq.qlogo.cn/g?b=oidb&k=klsg8iaqFHcFKoFk9xPicQKQ&s=640&t=1586703912","nickname":"事在人为.","yellow_vip_level":"0","figureurl_qq_1":"http://thirdqq.qlogo.cn/g?b=oidb&k=klsg8iaqFHcFKoFk9xPicQKQ&s=40&t=1586703912","vip":"0","figureurl_qq_2":"http://thirdqq.qlogo.cn/g?b=oidb&k=klsg8iaqFHcFKoFk9xPicQKQ&s=100&t=1586703912"}
 * 7.重定向到应用首页    return "redirect:/home";
 * <p>
 * <p>
 * 应用
 * <p>
 * 1.前端访问后端接口  /qq/oauth            GET方式
 * 后端重定向到QQ互联
 * 2.用户第三方登录，QQ互联获取用户信息
 * 3.QQ互联请求后端接口 /connect          GET方式
 * 后端获取QQ互联传来的信息
 * code>>>>>>>>>>A963EFBDF8A569F0A5C76B9273BAE19D
 * state>>>>>>>>>>A963EFBDF8A569F0A5C76B9273BAE19D
 * uuid>>>>>>>>>>040f78c2b71d462f8f3684c348f08cac
 * 后端通过Authorization Code获取Access Token    82EECEA6B22C343C60FB79FBE9C602E5
 * 后端请求QQ互联  获取回调后的 openid 值    "https://graph.qq.com/oauth2.0/me?access_token=" + access_token;
 * 后端获取QQ用户信息       jsonObject{"ret":0,"msg":"","is_lost":0,"gender":"男","is_yellow_vip":"0","city":"南京","year":"1999","level":"0","figureurl_2":"http://qzapp.qlogo.cn/qzapp/101883898/EA01EC237FA0A2FC1D2842D07C638E58/100","figureurl_1":"http://qzapp.qlogo.cn/qzapp/101883898/EA01EC237FA0A2FC1D2842D07C638E58/50","gender_type":1,"is_yellow_year_vip":"0","province":"江苏","constellation":"","figureurl":"http://qzapp.qlogo.cn/qzapp/101883898/EA01EC237FA0A2FC1D2842D07C638E58/30","figureurl_type":"1","figureurl_qq":"http://thirdqq.qlogo.cn/g?b=oidb&k=klsg8iaqFHcFKoFk9xPicQKQ&s=640&t=1586703912","nickname":"事在人为.","yellow_vip_level":"0","figureurl_qq_1":"http://thirdqq.qlogo.cn/g?b=oidb&k=klsg8iaqFHcFKoFk9xPicQKQ&s=40&t=1586703912","vip":"0","figureurl_qq_2":"http://thirdqq.qlogo.cn/g?b=oidb&k=klsg8iaqFHcFKoFk9xPicQKQ&s=100&t=1586703912"}
 * 4.后端重定向到前端
 * @createTime 2020年06月17日 15:35:00
 */

//此处只能用Controller接口，在换成@RestControllder时不会跳转，踩坑了。。。
@Slf4j
@Controller
@Api(value = "QQController", tags = {"QQ第三方登录接口"})
public class QQController {
    @Resource
    private QQService qqService;

    /**
     * @param session
     * @return
     */
    @ControllerWebLog(name = "qq", isSaved = true)
    @ApiOperation(value = "跳转到第三方", notes = "请求第三方登录")
    @GetMapping("/qq/oauth")
    public String qq(HttpSession session) {
        log.info("-----/qq/oauth被访问----**1**");
        return "redirect:" + qqService.qqRedirect(session);
    }


    /**
     * QQ回调
     *
     * @param request
     * @return
     */
    @ControllerWebLog(name = "connect", isSaved = true)
    @ApiOperation(value = "QQ互联回调用的", notes = "QQ互联回调")
    @GetMapping("/connect")
    public String connect(HttpServletRequest request) throws Exception {
        log.info("------QQ的服务器又重定向回来-----");
        log.info("-----/connect被访问----**1**");
        //如果没有绑定QQ，则返回openid
        return "redirect:" + qqService.connect(request);
    }
}
