package com.metadata.train.sys.service;

import com.metadata.train.entity.sys.User;

/**
 * 后台用户缓存操作类
 * Created by macro on 2020/3/13.
 */
public interface UserCacheService {
    /**
     * 删除后台用户缓存
     */
    void delUser(Long userId);

    /**
     * 获取缓存后台用户信息
     */
    User getUser(Long userId);

    /**
     * 设置缓存后台用户信息
     */
    void setUser(User user);
}
