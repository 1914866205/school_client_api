package com.niit.soft.client.api.util.Alioss;

import lombok.Data;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author wl
 * @ClassNameMyCallable
 * @Description TODO
 * @Date 2020/5/20
 * @Version 1.0
 */
@Data
@Component
@Async
public class MyCallable implements Callable<List<String>> {


    private List<File> fileList;
    private List<String> tempFiles;


    @Override
    public List<String> call() throws Exception {
        tempFiles = new ArrayList<>();
        for (File file : fileList) {
            String url = com.niit.soft.client.api.util.Alioss.AliossUtil.upload(file);
            tempFiles.add(url);


        }

        return tempFiles;
    }

}
