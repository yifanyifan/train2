package com.metadata.train.sys.controller;

import cn.hutool.core.collection.CollUtil;
import com.metadata.train.common.ResultEntity;
import com.metadata.train.entity.sys.Role;
import com.metadata.train.entity.sys.User;
import com.metadata.train.sys.param.RolePageParam;
import com.metadata.train.sys.service.LoginService;
import com.metadata.train.sys.service.PermissionService;
import com.metadata.train.sys.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/sso")
@Api(value = "登录管理", tags = {"登录管理"})
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        try {
            return loginService.login(username, password, request);
        } catch (Exception e) {
                log.info(e.getMessage(), e);
            return ResultEntity.failed("登录异常" + e.getMessage());
        }
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getAdminInfo(HttpServletRequest request) throws Exception {
        User user = loginService.getCurrentUser(request);
        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("menus", permissionService.getMenuList(user.getId()));
        List<Role> roleList = roleService.getRoleByUser(user.getId());
        if (CollUtil.isNotEmpty(roleList)) {
            List<String> roles = roleList.stream().map(Role::getName).collect(Collectors.toList());
            data.put("roles", roles);
        }
        return ResultEntity.success(data);
    }
/*

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String password,
                                       @RequestParam String authCode) {
        memberService.updatePassword(telephone,password,authCode);
        return CommonResult.success(null,"密码修改成功");
    }

    @ApiOperation("根据用户名获取通用用户信息")
    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    @ResponseBody
    public UserDto loadUserByUsername(@RequestParam String username) {
        return memberService.loadUserByUsername(username);
    }*/
}
