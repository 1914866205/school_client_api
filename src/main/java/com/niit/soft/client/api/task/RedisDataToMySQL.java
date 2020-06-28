package com.niit.soft.client.api.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.soft.client.api.domain.model.schoolmate.Dynamic;
import com.niit.soft.client.api.domain.model.schoolmate.Thumb;
import com.niit.soft.client.api.repository.schoolmate.DynamicRepository;
import com.niit.soft.client.api.service.schoolmate.ThumbService;
import com.niit.soft.client.api.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName redisDataToMySQL
 * @Description 同步redis到mysql 点赞数
 * @Author xiaobinggan
 * @Date 2020/6/9 9:04 下午
 * @Version 1.0
 **/
@Component
@Slf4j
public class RedisDataToMySQL {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private DynamicRepository dynamicRepository;

    @Resource
    private ThumbService thumbService;


    // 每十分钟
//    @Scheduled(cron = "0 */10 * * * ?")
    // 每五秒执行
//    @Scheduled(cron = "*/5 * * * * ?")
    // 每小时
    @Scheduled(cron = "0 0 * * * ?")
    public void redisDataToMySQL() {
        log.info("定时同步数据库 时间：{}", LocalDateTime.now());

        List<Thumb> thumbList = new ArrayList<>();
        List<Thumb> thumbListDel = new ArrayList<>();
        // 获取所有资讯的id
        for (Dynamic dynamic : dynamicRepository.findAll()) {
//            log.info("" + dynamic.toString());
            Map<Object, Object> hmget = redisUtil.hmget(String.valueOf(dynamic.getPkDynamicId()));

            // 从redis取出所有点赞信息
            for (Map.Entry<Object, Object> entry : hmget.entrySet()) {
                thumbList.add(Thumb.builder().pkThumbId((String) entry.getKey())
                        .userId((String) entry.getValue())
                        .dynamicId(dynamic.getPkDynamicId())
                        .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                        .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                        .isDeleted(false).build());
            }
//            log.info("redis的点赞记录:{}", thumbList);
            // 保存更新
            thumbService.saveOrUpdateBatch(thumbList);


            List<String> id = new ArrayList<>();

            // 取出mysql中点赞信息
            for (Thumb thumb : thumbService.list(new QueryWrapper<Thumb>().eq("dynamic_id", dynamic.getPkDynamicId()))) {
                id.add(thumb.getPkThumbId());
            }
            List<String> haveBe = id;
            int size = 0;

            // 取出redis中点赞信息
            for (Object o : hmget.keySet()) {
                // 如果mysql中信息包含redis信息
                if (id.contains((String) o)) {
                    // 则移除
                    id.remove((String) o);
//                    记录redis有多少条点赞记录
                    size++;
                }
            }
            List<String> shouldDelete = id;

            // 逻辑删除redis的数据不在mysql中的数据
            if (id.size() != 0) {
                for (Thumb thumb : thumbService.listByIds(id)) {
                    thumb.setIsDeleted(true);
                    thumbListDel.add(thumb);
                }
            }

            dynamic.setThumbs(size);
            haveBe.removeAll(shouldDelete);

            dynamicRepository.saveAndFlush(dynamic);

//            log.info("redis的需要删除的点赞记录:{}", thumbListDel);
            thumbService.updateBatchById(thumbListDel);

        }

    }

}
