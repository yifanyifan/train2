package com.metadata.train.sys.service;

import com.metadata.train.common.ResultEntity;
import com.metadata.train.entity.sys.StudentRegistry;
import com.metadata.train.sys.param.StudentRegistryPageParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metadata.train.entity.common.PageEntity;
import java.util.List;

/**
 * 学员注册表 服务类
 *
 * @author 易樊
 * @since 2022-04-07
 */
public interface StudentRegistryService extends IService<StudentRegistry> {

    /**
     * 保存
     *
     * @param studentRegistry
     * @return
     * @throws Exception
     */
    ResultEntity saveStudentRegistry(StudentRegistry studentRegistry) throws Exception;

    /**
     * 修改
     *
     * @param studentRegistry
     * @return
     * @throws Exception
     */
    boolean updateStudentRegistry(StudentRegistry studentRegistry) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteStudentRegistry(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param studentRegistryPageParam
     * @return
     * @throws Exception
     */
    PageEntity<StudentRegistry> getStudentRegistryPageList(StudentRegistryPageParam studentRegistryPageParam) throws Exception;

    /**
     * 获取列表对象
     *
     * @param studentRegistryPageParam
     * @return
     * @throws Exception
     */
    List<StudentRegistry> getStudentRegistryList(StudentRegistryPageParam studentRegistryPageParam) throws Exception;

}
