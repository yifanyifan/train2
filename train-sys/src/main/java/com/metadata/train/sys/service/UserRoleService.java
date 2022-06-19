package com.metadata.train.sys.service;

import com.metadata.train.entity.sys.UserRole;
import com.metadata.train.sys.param.UserRolePageParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metadata.train.entity.common.PageEntity;
import java.util.List;

/**
 * 用户角色表 服务类
 *
 * @author 易樊
 * @since 2022-04-07
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 保存
     *
     * @param userRole
     * @return
     * @throws Exception
     */
    boolean saveUserRole(UserRole userRole) throws Exception;

    /**
     * 修改
     *
     * @param userRole
     * @return
     * @throws Exception
     */
    boolean updateUserRole(UserRole userRole) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteUserRole(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param userRolePageParam
     * @return
     * @throws Exception
     */
    PageEntity<UserRole> getUserRolePageList(UserRolePageParam userRolePageParam) throws Exception;

    /**
     * 获取列表对象
     *
     * @param userRolePageParam
     * @return
     * @throws Exception
     */
    List<UserRole> getUserRoleList(UserRolePageParam userRolePageParam) throws Exception;

}
