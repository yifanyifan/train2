package com.metadata.train.sys.controller;

import com.metadata.train.entity.sys.Permission;
import com.metadata.train.sys.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import com.metadata.train.sys.param.PermissionPageParam;
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
import java.util.Map;

/**
 *  控制器
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Slf4j
@RestController
@RequestMapping("/permission")
@Api(value = "API", tags = {""})
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 添加
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加", response = ResultEntity.class)
    public ResultEntity<Boolean> addPermission(@Validated(Add.class) @RequestBody Permission permission) throws Exception {
        boolean flag = permissionService.savePermission(permission);
        return ResultEntity.success(flag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", response = ResultEntity.class)
    public ResultEntity<Boolean> updatePermission(@Validated(Update.class) @RequestBody Permission permission) throws Exception {
        boolean flag = permissionService.updatePermission(permission);
        return ResultEntity.success(flag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除", response = ResultEntity.class)
    public ResultEntity<Boolean> deletePermission(@PathVariable("id") Long id) throws Exception {
        boolean flag = permissionService.deletePermission(id);
        return ResultEntity.success(flag);
    }

    /**
     * 获取详情
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "详情", response = Permission.class)
    public ResultEntity<Permission> getPermission(@PathVariable("id") Long id) throws Exception {
        Permission permission = permissionService.getById(id);
        return ResultEntity.success(permission);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "分页列表", response = Permission.class)
    public ResultEntity<PageEntity<Permission>> getPermissionPageList(@Validated @RequestBody PermissionPageParam permissionPageParam) throws Exception {
        PageEntity<Permission> paging = permissionService.getPermissionPageList(permissionPageParam);
        return ResultEntity.success(paging);
    }

    /**
     * 列表
     */
    @PostMapping("/getList")
    @ApiOperation(value = "列表", response = Permission.class)
    @ApiOperationSupport(ignoreParameters = {"permissionPageParam.pageIndex","permissionPageParam.pageSorts","permissionPageParam.pageSize"})
    public ResultEntity<List<Permission>> getPermissionList(@Validated @RequestBody PermissionPageParam permissionPageParam) throws Exception {
        List<Permission> list = permissionService.getPermissionList(permissionPageParam);
        return ResultEntity.success(list);
    }

    @GetMapping("/getUrlAndPermissionAll")
    @ApiOperation(value = "获取所有URL和权限的关联关系")
    public Map<String, String> getUrlAndPermissionAll() throws Exception {
        Map<String, String> map = permissionService.getUrlAndPermissionAll();
        return map;
    }
}

