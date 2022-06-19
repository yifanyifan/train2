package com.metadata.train.sys.service.impl;

import com.metadata.train.entity.sys.LogOp;
import com.metadata.train.sys.mapper.LogOpMapper;
import com.metadata.train.sys.service.LogOpService;
import com.metadata.train.sys.param.LogOpPageParam;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metadata.train.entity.common.PageEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * 操作日志表 服务实现类
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Slf4j
@Service
public class LogOpServiceImpl extends ServiceImpl<LogOpMapper, LogOp> implements LogOpService {

    @Autowired
    private LogOpMapper logOpMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveLogOp(LogOp logOp) throws Exception {
        return super.save(logOp);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateLogOp(LogOp logOp) throws Exception {
        return super.updateById(logOp);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteLogOp(Long id) throws Exception {
        return super.removeById(id);
    }


    @Override
    public PageEntity<LogOp> getLogOpPageList(LogOpPageParam logOpPageParam) throws Exception {
        Page<LogOp> page = new Page<>(logOpPageParam.getPageIndex(), logOpPageParam.getPageSize());
        LambdaQueryWrapper<LogOp> wrapper = getLambdaQueryWrapper(logOpPageParam);
        IPage<LogOp> iPage = logOpMapper.selectPage(page, wrapper);
        return new PageEntity<LogOp>(iPage);
    }

    @Override
    public List<LogOp> getLogOpList(LogOpPageParam logOpPageParam) throws Exception {
        LambdaQueryWrapper<LogOp> wrapper = getLambdaQueryWrapper(logOpPageParam);
        List<LogOp> LogOpList = logOpMapper.selectList(wrapper);
        return LogOpList;
    }

    private LambdaQueryWrapper<LogOp> getLambdaQueryWrapper(LogOpPageParam logOpPageParam) {
        LambdaQueryWrapper<LogOp> wrapper = new LambdaQueryWrapper<>();
        return wrapper;
    }

}
