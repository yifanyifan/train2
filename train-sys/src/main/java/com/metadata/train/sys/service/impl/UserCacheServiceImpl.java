package com.metadata.train.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.metadata.train.entity.sys.User;
import com.metadata.train.service.RedisService;
import com.metadata.train.sys.service.UserCacheService;
import com.metadata.train.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * UmsUserCacheService实现类
 * Created by macro on 2020/3/13.
 */
@Service
public class UserCacheServiceImpl implements UserCacheService {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.user}")
    private String REDIS_KEY_ADMIN;

    @Override
    public void delUser(Long userId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + userId;
        redisService.del(key);
    }

    @Override
    public User getUser(Long userId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + userId;
        User user = JSON.parseObject((String) redisService.get(key), User.class);
        return user;
    }

    @Override
    public void setUser(User user) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + user.getId();
        redisService.set(key, JSON.toJSONString(user), REDIS_EXPIRE);
    }
}
