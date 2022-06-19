package com.metadata.train.sys.controller;

import com.metadata.train.entity.sys.Role;
import com.metadata.train.sys.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import com.metadata.train.sys.param.RolePageParam;
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
 *  控制器
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Slf4j
@RestController
@RequestMapping("/role")
@Api(value = "API", tags = {""})
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 添加
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加", response = ResultEntity.class)
    public ResultEntity<Boolean> addRole(@Validated(Add.class) @RequestBody Role role) throws Exception {
        boolean flag = roleService.saveRole(role);
        return ResultEntity.success(flag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", response = ResultEntity.class)
    public ResultEntity<Boolean> updateRole(@Validated(Update.class) @RequestBody Role role) throws Exception {
        boolean flag = roleService.updateRole(role);
        return ResultEntity.success(flag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除", response = ResultEntity.class)
    public ResultEntity<Boolean> deleteRole(@PathVariable("id") Long id) throws Exception {
        boolean flag = roleService.deleteRole(id);
        return ResultEntity.success(flag);
    }

    /**
     * 获取详情
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "详情", response = Role.class)
    public ResultEntity<Role> getRole(@PathVariable("id") Long id) throws Exception {
        Role role = roleService.getById(id);
        return ResultEntity.success(role);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "分页列表", response = Role.class)
    public ResultEntity<PageEntity<Role>> getRolePageList(@Validated @RequestBody RolePageParam rolePageParam) throws Exception {
        PageEntity<Role> paging = roleService.getRolePageList(rolePageParam);
        return ResultEntity.success(paging);
    }

    /**
     * 列表
     */
    @PostMapping("/getList")
    @ApiOperation(value = "列表", response = Role.class)
    @ApiOperationSupport(ignoreParameters = {"rolePageParam.pageIndex","rolePageParam.pageSorts","rolePageParam.pageSize"})
    public ResultEntity<List<Role>> getRoleList(@Validated @RequestBody RolePageParam rolePageParam) throws Exception {
        List<Role> list = roleService.getRoleList(rolePageParam);
        return ResultEntity.success(list);
    }

}

