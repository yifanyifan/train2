package com.metadata.train.sys.controller;

import com.metadata.train.entity.sys.UserRole;
import com.metadata.train.sys.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import com.metadata.train.sys.param.UserRolePageParam;
import com.metadata.train.common.ResultEntity;
import com.metadata.train.entity.common.PageEntity;
import com.metadata.train.validator.groups.Add;
import com.metadata.train.validator.groups.Update;
import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户角色表 控制器
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Slf4j
@RestController
@RequestMapping("/userRole")
@Api(value = "用户角色表API", tags = {"用户角色表"})
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 添加用户角色表
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加用户角色表", response = ResultEntity.class)
    public ResultEntity<Boolean> addUserRole(@Validated(Add.class) @RequestBody UserRole userRole) throws Exception {
        boolean flag = userRoleService.saveUserRole(userRole);
        return ResultEntity.success(flag);
    }

    /**
     * 修改用户角色表
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改用户角色表", response = ResultEntity.class)
    public ResultEntity<Boolean> updateUserRole(@Validated(Update.class) @RequestBody UserRole userRole) throws Exception {
        boolean flag = userRoleService.updateUserRole(userRole);
        return ResultEntity.success(flag);
    }

    /**
     * 删除用户角色表
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除用户角色表", response = ResultEntity.class)
    public ResultEntity<Boolean> deleteUserRole(@PathVariable("id") Long id) throws Exception {
        boolean flag = userRoleService.deleteUserRole(id);
        return ResultEntity.success(flag);
    }

    /**
     * 获取用户角色表详情
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "用户角色表详情", response = UserRole.class)
    public ResultEntity<UserRole> getUserRole(@PathVariable("id") Long id) throws Exception {
        UserRole userRole = userRoleService.getById(id);
        return ResultEntity.success(userRole);
    }

    /**
     * 用户角色表分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "用户角色表分页列表", response = UserRole.class)
    public ResultEntity<PageEntity<UserRole>> getUserRolePageList(@Validated @RequestBody UserRolePageParam userRolePageParam) throws Exception {
        PageEntity<UserRole> paging = userRoleService.getUserRolePageList(userRolePageParam);
        return ResultEntity.success(paging);
    }

    /**
     * 用户角色表列表
     */
    @PostMapping("/getList")
    @ApiOperation(value = "用户角色表列表", response = UserRole.class)
    @ApiOperationSupport(ignoreParameters = {"userRolePageParam.pageIndex","userRolePageParam.pageSorts","userRolePageParam.pageSize"})
    public ResultEntity<List<UserRole>> getUserRoleList(@Validated @RequestBody UserRolePageParam userRolePageParam) throws Exception {
        List<UserRole> list = userRoleService.getUserRoleList(userRolePageParam);
        return ResultEntity.success(list);
    }

}

