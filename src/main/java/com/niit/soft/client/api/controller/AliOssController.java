package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.util.Alioss.FileUtil;
import com.niit.soft.client.api.util.Alioss.MyCallable;
import com.niit.soft.client.api.util.Alioss.ThreadPoolTest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wl
 * @ClassNameAliOssController
 * @Description TODO
 * @Date 2020/5/20
 * @Version 1.0
 */
@RestController
@Slf4j
@Api(tags = "图片上传")
@RequestMapping(value = "/api")
public class AliOssController {
    @Resource
    private ThreadPoolTest threadPoolTest;
    @Resource
    private MyCallable myCallable;

    @PostMapping("/uploadimg")
    @ApiOperation(value = "图片上传 ", notes = "请求参数file")
    public List<String> uploadImg(@RequestParam("file") MultipartFile[] multipartFiles) {
        myCallable.setFileList(FileUtil.getFiles(multipartFiles));

        return threadPoolTest.getUrl();
    }
}
