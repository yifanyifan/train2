package com.metadata.train.utils;

import cn.hutool.json.JSONString;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.metadata.train.auth.AuthConstant;
import com.metadata.train.entity.sys.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
public class TrainSessionUtil {
    private RedisTemplate<String, Object> redisTemplate;
    private HttpServletRequest request;
    private String sessionRedisKey;

    private ThreadLocal<Object> cache = new ThreadLocal<Object>();

    private TrainSessionUtil() {
    }

    private TrainSessionUtil(RedisTemplate<String, Object> redisTemplate, HttpServletRequest request) {
        this.redisTemplate = redisTemplate;
        this.request = request;
        Assert.notNull(request, "request must not be null");
        this.sessionRedisKey = getSessionKey();
    }

    public static TrainSessionUtil build(RedisTemplate<String, Object> redisTemplate, HttpServletRequest request) {
        return new TrainSessionUtil(redisTemplate, request);
    }

    private String getSessionKey() {
        String token = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        Assert.notNull(token, "trainSessionId must not be null");

        JSONObject userJsonObject = JSONObject.parseObject(token);
        String id = userJsonObject.getString("id"); // 用户id

        return trainSessionId(id);
    }

    public String trainSessionId(String sessionId) {
        Assert.notNull(sessionId, "sessionId must not be null");
        return "sys:ums:".concat(sessionId);
    }

    public User getSessionUser() {
        Object user = cache.get();
        user = null == user ? redisTemplate.opsForValue().get(sessionRedisKey) : user;
        if (null != user) {
            String userInfo = String.valueOf(user);
            return JSON.parseObject(userInfo, User.class);
        } else {
            return null;
        }
    }

    /**
     * 清除session
     *
     * @return
     */
    public boolean clearSession() {
        return redisTemplate.delete(sessionRedisKey);
    }
}
