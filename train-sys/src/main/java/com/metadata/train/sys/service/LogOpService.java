package com.metadata.train.sys.service;

import com.metadata.train.entity.sys.LogOp;
import com.metadata.train.sys.param.LogOpPageParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metadata.train.entity.common.PageEntity;
import java.util.List;

/**
 * 操作日志表 服务类
 *
 * @author 易樊
 * @since 2022-04-07
 */
public interface LogOpService extends IService<LogOp> {

    /**
     * 保存
     *
     * @param logOp
     * @return
     * @throws Exception
     */
    boolean saveLogOp(LogOp logOp) throws Exception;

    /**
     * 修改
     *
     * @param logOp
     * @return
     * @throws Exception
     */
    boolean updateLogOp(LogOp logOp) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteLogOp(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param logOpPageParam
     * @return
     * @throws Exception
     */
    PageEntity<LogOp> getLogOpPageList(LogOpPageParam logOpPageParam) throws Exception;

    /**
     * 获取列表对象
     *
     * @param logOpPageParam
     * @return
     * @throws Exception
     */
    List<LogOp> getLogOpList(LogOpPageParam logOpPageParam) throws Exception;

}
