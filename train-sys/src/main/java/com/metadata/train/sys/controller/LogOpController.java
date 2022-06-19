package com.metadata.train.sys.controller;

import com.metadata.train.entity.sys.LogOp;
import com.metadata.train.sys.service.LogOpService;
import lombok.extern.slf4j.Slf4j;
import com.metadata.train.sys.param.LogOpPageParam;
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
 * 操作日志表 控制器
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Slf4j
@RestController
@RequestMapping("/logOp")
@Api(value = "操作日志表API", tags = {"操作日志表"})
public class LogOpController {

    @Autowired
    private LogOpService logOpService;

    /**
     * 添加操作日志表
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加操作日志表", response = ResultEntity.class)
    public ResultEntity<Boolean> addLogOp(@Validated(Add.class) @RequestBody LogOp logOp) throws Exception {
        boolean flag = logOpService.saveLogOp(logOp);
        return ResultEntity.success(flag);
    }

    /**
     * 修改操作日志表
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改操作日志表", response = ResultEntity.class)
    public ResultEntity<Boolean> updateLogOp(@Validated(Update.class) @RequestBody LogOp logOp) throws Exception {
        boolean flag = logOpService.updateLogOp(logOp);
        return ResultEntity.success(flag);
    }

    /**
     * 删除操作日志表
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除操作日志表", response = ResultEntity.class)
    public ResultEntity<Boolean> deleteLogOp(@PathVariable("id") Long id) throws Exception {
        boolean flag = logOpService.deleteLogOp(id);
        return ResultEntity.success(flag);
    }

    /**
     * 获取操作日志表详情
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "操作日志表详情", response = LogOp.class)
    public ResultEntity<LogOp> getLogOp(@PathVariable("id") Long id) throws Exception {
        LogOp logOp = logOpService.getById(id);
        return ResultEntity.success(logOp);
    }

    /**
     * 操作日志表分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "操作日志表分页列表", response = LogOp.class)
    public ResultEntity<PageEntity<LogOp>> getLogOpPageList(@Validated @RequestBody LogOpPageParam logOpPageParam) throws Exception {
        PageEntity<LogOp> paging = logOpService.getLogOpPageList(logOpPageParam);
        return ResultEntity.success(paging);
    }

    /**
     * 操作日志表列表
     */
    @PostMapping("/getList")
    @ApiOperation(value = "操作日志表列表", response = LogOp.class)
    @ApiOperationSupport(ignoreParameters = {"logOpPageParam.pageIndex","logOpPageParam.pageSorts","logOpPageParam.pageSize"})
    public ResultEntity<List<LogOp>> getLogOpList(@Validated @RequestBody LogOpPageParam logOpPageParam) throws Exception {
        List<LogOp> list = logOpService.getLogOpList(logOpPageParam);
        return ResultEntity.success(list);
    }

}

