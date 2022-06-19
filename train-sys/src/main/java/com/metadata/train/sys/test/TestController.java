package com.metadata.train.sys.test;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSONObject;
import com.metadata.train.auth.AuthConstant;
import com.metadata.train.entity.sys.User;
import com.metadata.train.utils.TrainSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取登录用户信息接口
 * Created by macro on 2020/6/19.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/currentUser")
    public User currentUser(HttpServletRequest request1) {
        User user = TrainSessionUtil.build(redisTemplate, request1).getSessionUser();
        return user;
    }

}
