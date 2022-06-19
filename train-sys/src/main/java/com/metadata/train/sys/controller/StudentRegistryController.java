package com.metadata.train.sys.controller;

import com.metadata.train.entity.sys.StudentRegistry;
import com.metadata.train.sys.service.StudentRegistryService;
import lombok.extern.slf4j.Slf4j;
import com.metadata.train.sys.param.StudentRegistryPageParam;
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
 * 学员注册表 控制器
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Slf4j
@RestController
@RequestMapping("/studentRegistry")
@Api(value = "学员注册表API", tags = {"学员注册表"})
public class StudentRegistryController {

    @Autowired
    private StudentRegistryService studentRegistryService;

    /**
     * 添加学员注册表
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加学员注册表", response = ResultEntity.class)
    public ResultEntity<Boolean> addStudentRegistry(@Validated(Add.class) @RequestBody StudentRegistry studentRegistry) throws Exception {
        return studentRegistryService.saveStudentRegistry(studentRegistry);
    }

    /**
     * 修改学员注册表
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改学员注册表", response = ResultEntity.class)
    public ResultEntity<Boolean> updateStudentRegistry(@Validated(Update.class) @RequestBody StudentRegistry studentRegistry) throws Exception {
        boolean flag = studentRegistryService.updateStudentRegistry(studentRegistry);
        return ResultEntity.success(flag);
    }

    /**
     * 删除学员注册表
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除学员注册表", response = ResultEntity.class)
    public ResultEntity<Boolean> deleteStudentRegistry(@PathVariable("id") Long id) throws Exception {
        boolean flag = studentRegistryService.deleteStudentRegistry(id);
        return ResultEntity.success(flag);
    }

    /**
     * 获取学员注册表详情
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "学员注册表详情", response = StudentRegistry.class)
    public ResultEntity<StudentRegistry> getStudentRegistry(@PathVariable("id") Long id) throws Exception {
        StudentRegistry studentRegistry = studentRegistryService.getById(id);
        return ResultEntity.success(studentRegistry);
    }

    /**
     * 学员注册表分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "学员注册表分页列表", response = StudentRegistry.class)
    public ResultEntity<PageEntity<StudentRegistry>> getStudentRegistryPageList(@Validated @RequestBody StudentRegistryPageParam studentRegistryPageParam) throws Exception {
        PageEntity<StudentRegistry> paging = studentRegistryService.getStudentRegistryPageList(studentRegistryPageParam);
        return ResultEntity.success(paging);
    }

    /**
     * 学员注册表列表
     */
    @PostMapping("/getList")
    @ApiOperation(value = "学员注册表列表", response = StudentRegistry.class)
    @ApiOperationSupport(ignoreParameters = {"studentRegistryPageParam.pageIndex","studentRegistryPageParam.pageSorts","studentRegistryPageParam.pageSize"})
    public ResultEntity<List<StudentRegistry>> getStudentRegistryList(@Validated @RequestBody StudentRegistryPageParam studentRegistryPageParam) throws Exception {
        List<StudentRegistry> list = studentRegistryService.getStudentRegistryList(studentRegistryPageParam);
        return ResultEntity.success(list);
    }

}

