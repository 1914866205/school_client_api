package com.niit.soft.client.api.util.Alioss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wl
 * @ClassNameThreadPool
 * @Description TODO
 * @Date 2020/5/20
 * @Version 1.0
 */
@Component
@Slf4j
public class ThreadPoolTest {
    @Resource
    private MyCallable myCallable;

    public List<String> getUrl() {
        List<String> returnValue = null;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(40));
        Future<List<String>> submit = threadPoolExecutor.submit(myCallable);


        //任务完成
        while (true) {

            if (submit.isDone()) {

                try {
                    //获取值
                    returnValue = submit.get();

                    System.out.println("线程返回值" + returnValue);

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                threadPoolExecutor.shutdown();
                break;
            }
        }
        return returnValue;
    }
}
