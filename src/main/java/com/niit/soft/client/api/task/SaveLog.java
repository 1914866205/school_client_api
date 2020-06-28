package com.niit.soft.client.api.task;

import com.niit.soft.client.api.domain.model.Loginfo;
import com.niit.soft.client.api.repository.LogInfoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/14 19:34
 * @Version 1.0
 **/
//注：如果这个类不加@EnableScheduling 注解，就需要在启动主类加上
@Component
public class SaveLog {
    Loginfo log = null;
    @Resource
    private LogInfoRepository logInfoRepository;

    /**
     * 这个类先不要动
     *
     * @throws IOException
     */
    @Scheduled(cron = "0 0 * * * ?")
    private void saveLog() throws IOException {
        System.out.println("-----开始保存日志------");
        BufferedReader bufferedReader = null;
        FileWriter fw = null;
        File file = new File("\\usr\\local\\logs");
        String[] fileList = file.list();
        String tempLine = null;
        assert fileList != null;
        for (int i = 0; i < fileList.length; i++) {
            File readFile = new File("\\usr\\local\\logs" + "\\" + fileList[i]);
            bufferedReader = new BufferedReader(new FileReader(readFile));
            if (readFile.getName().startsWith("info")) {
                System.out.println("-----开始保存info日志------");
                while ((tempLine = bufferedReader.readLine()) != null) {
                    //判断标志符
                    if (tempLine.endsWith("**1**")) {
                        System.out.println(tempLine);
                        insertInfo(tempLine);
                    }
                }
            }
            fw = new FileWriter(readFile);  //删除之前数据，重新写
//            fw = new FileWriter(readFile, true);   接着之前的数据，续写
            fw.write("");
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (fw != null) {
            fw.close();
        }
        System.out.println("-----保存日志完毕------");
    }

    protected void insertInfo(String logInfo) {
//        String str = "2020-05-30 08:51:28.708  INFO 22128 --- [http-nio-8080-exec-9] c.n.soft.client.api.aspect.WebLogAspect  : 请求方法：POST**1**\n".trim();
//        System.out.println("******************************");
        System.out.println(logInfo.substring(0, 24)); //日志时间
        System.out.println(logInfo.split("---")[1].split(":")[0].trim()); //执行类名
        System.out.println(logInfo.split(":")[3].replace("**1**", ""));//日志信息
        this.log = Loginfo.builder()
                .executionTime(Timestamp.valueOf(logInfo.substring(0, 24)))
                .className(logInfo.split("---")[1].split(":")[0].trim())
                .content(logInfo.split(":")[3].replace("**1**", ""))
                .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false)
                .build();
        logInfoRepository.save(log);
    }
}

