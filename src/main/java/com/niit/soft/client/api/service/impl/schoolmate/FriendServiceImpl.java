package com.niit.soft.client.api.service.impl.schoolmate;

import com.niit.soft.client.api.domain.model.UserAccount;
import com.niit.soft.client.api.repository.UserAccountRepository;
import com.niit.soft.client.api.service.schoolmate.FriendService;
import com.niit.soft.client.api.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FriendServiceImpl
 * @Description 推荐好友
 * @Author xiaobinggan
 * @Date 2020/6/24 9:15 上午
 * @Version 1.0
 **/
@Service
public class FriendServiceImpl implements FriendService {
    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserAccountRepository userAccountRepository;

    @Override
    public void embedding(String userId, String tag) {
        UserAccount userAccountByInfo = userAccountRepository.findUserAccountByInfo(userId);
        List<Object> objects = redisUtil.lGet(tag, 0, -1);
        assert objects != null;
        if (objects.size() == 0) {
            redisUtil.lSet(tag, userAccountByInfo);
        } else {
            List<String> ids = new ArrayList<>();
            for (Object object : objects) {
                UserAccount userAccount = (UserAccount) object;
                ids.add(userAccount.getPkUserAccountId());
            }
            if (!ids.contains(userId)) {
                redisUtil.lSet(tag, userAccountByInfo);
            }
        }

    }

    @Override
    public List<Object> recommendFriends(String userId, String tag) {
        List<Object> objects = redisUtil.lGet(tag, 0, -1);
        for (Object object : objects) {
            UserAccount userAccount = (UserAccount) object;
            if (userAccount.getPkUserAccountId().equals(userId)) {
                objects.remove(userAccount);
                break;
            }
        }
        return objects;
    }
}
