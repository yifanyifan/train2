package com.metadata.train.sys.service;

import com.metadata.train.entity.sys.Role;
import com.metadata.train.sys.param.RolePageParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metadata.train.entity.common.PageEntity;
import java.util.List;

/**
 *  服务类
 *
 * @author 易樊
 * @since 2022-04-07
 */
public interface RoleService extends IService<Role> {

    /**
     * 保存
     *
     * @param role
     * @return
     * @throws Exception
     */
    boolean saveRole(Role role) throws Exception;

    /**
     * 修改
     *
     * @param role
     * @return
     * @throws Exception
     */
    boolean updateRole(Role role) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteRole(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param rolePageParam
     * @return
     * @throws Exception
     */
    PageEntity<Role> getRolePageList(RolePageParam rolePageParam) throws Exception;

    /**
     * 获取列表对象
     *
     * @param rolePageParam
     * @return
     * @throws Exception
     */
    List<Role> getRoleList(RolePageParam rolePageParam) throws Exception;

    List<Role> getRoleByUser(Long id);
}
