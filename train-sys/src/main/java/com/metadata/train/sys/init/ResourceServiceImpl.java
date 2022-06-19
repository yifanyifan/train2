package com.metadata.train.sys.init;

import com.metadata.train.auth.AuthConstant;
import com.metadata.train.sys.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
@Slf4j
public class ResourceServiceImpl {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private PermissionService permissionService;

    @PostConstruct
    public void initData() {
        log.info("================> 开始初始化 Redis 用户菜单 集合");
        Map<String, String> map = permissionService.getUrlAndPermissionAll();

        redisTemplate.opsForHash().putAll(AuthConstant.RESOURCE_ROLES_MAP_KEY, map);
        log.info("================> 结束初始化 Redis 用户菜单 集合");
    }
}
