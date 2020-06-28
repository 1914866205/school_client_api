package com.niit.soft.client.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName PageController
 * @Description 访问静态资源
 * @Author xiaobinggan
 * @Date 2020/6/12 11:14 上午
 * @Version 1.0
 **/
@Controller
public class PageController {

    @GetMapping("/show")
    public String show() {
        return "/show.html";
    }
}
