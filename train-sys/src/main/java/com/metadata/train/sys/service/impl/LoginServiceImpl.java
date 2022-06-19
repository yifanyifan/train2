package com.metadata.train.sys.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.metadata.train.auth.AuthConstant;
import com.metadata.train.auth.Oauth2TokenDto;
import com.metadata.train.common.ResultEntity;
import com.metadata.train.common.ResultEntityEnum;
import com.metadata.train.common.TrainException;
import com.metadata.train.entity.sys.Role;
import com.metadata.train.entity.sys.User;
import com.metadata.train.service.RedisService;
import com.metadata.train.sys.feign.AuthCilents;
import com.metadata.train.sys.mapper.UserMapper;
import com.metadata.train.sys.service.LoginService;
import com.metadata.train.sys.service.RoleService;
import com.metadata.train.sys.service.UserCacheService;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务实现类
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthCilents authCilents;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserCacheService userCacheService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RoleService roleService;

    @Override
    public ResultEntity login(String username, String password, HttpServletRequest request) throws Exception {
        if (StrUtil.isEmpty(username) || StrUtil.isEmpty(password)) {
            return ResultEntity.failed("用户名或密码不能为空！");
        }
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstant.ADMIN_CLIENT_ID);
        params.put("client_secret", "123456");
        params.put("grant_type", "password");
        params.put("username", username);
        params.put("password", password);
        ResultEntity<Oauth2TokenDto> resultEntity = authCilents.getToken(params);
        if (String.valueOf(ResultEntityEnum.SUCCESS.getCode()).equals(String.valueOf(resultEntity.getCode())) && resultEntity.getData() != null) {
            // user存入redis
            Oauth2TokenDto oauth2TokenDto = resultEntity.getData();
            String realToken = oauth2TokenDto.getToken().replace("Bearer ", "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            JSONObject userJsonObject = JSONObject.parseObject(userStr);
            User user = userMapper.selectById(userJsonObject.getString("id"));
            List<Role> roleList = roleService.getRoleByUser(user.getId());
            user.setRoleList(roleList);
            String userJson = JSON.toJSONString(user);
            redisTemplate.opsForValue().set("sys:ums:" + user.getId(), userJson);

            //insertLoginLog(username);
        }
        return resultEntity;
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        if (StrUtil.isEmpty(userStr)) {
            throw new TrainException(ResultEntityEnum.UNAUTHORIZED);
        }
        User userDto = JSONUtil.toBean(userStr, User.class);
        User user = userCacheService.getUser(userDto.getId());
        if (ObjectUtil.isNotEmpty(user)) {
            return user;
        } else {
            user = userMapper.selectById(userDto.getId());
            userCacheService.setUser(user);
            return user;
        }
    }
}
