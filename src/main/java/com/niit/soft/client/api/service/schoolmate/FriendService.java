package com.niit.soft.client.api.service.schoolmate;

import java.util.List;

/**
 * @ClassName FriendService
 * @Description 推荐好友
 * @Author xiaobinggan
 * @Date 2020/6/24 9:13 上午
 * @Version 1.0
 **/
public interface FriendService {
    /**
     * 推荐好友
     *
     * @param userId
     * @param tag
     * @return
     */
    public List<Object> recommendFriends(String userId, String tag);

    /**
     * 埋包
     *
     * @param userId
     * @param tag
     */
    public void embedding(String userId, String tag);
}