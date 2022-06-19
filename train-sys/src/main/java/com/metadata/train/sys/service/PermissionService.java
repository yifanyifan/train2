package com.metadata.train.sys.service;

import com.metadata.train.entity.sys.Permission;
import com.metadata.train.sys.param.PermissionPageParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metadata.train.entity.common.PageEntity;
import java.util.List;
import java.util.Map;

/**
 *  服务类
 *
 * @author 易樊
 * @since 2022-04-07
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 保存
     *
     * @param permission
     * @return
     * @throws Exception
     */
    boolean savePermission(Permission permission) throws Exception;

    /**
     * 修改
     *
     * @param permission
     * @return
     * @throws Exception
     */
    boolean updatePermission(Permission permission) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deletePermission(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param permissionPageParam
     * @return
     * @throws Exception
     */
    PageEntity<Permission> getPermissionPageList(PermissionPageParam permissionPageParam) throws Exception;

    /**
     * 获取列表对象
     *
     * @param permissionPageParam
     * @return
     * @throws Exception
     */
    List<Permission> getPermissionList(PermissionPageParam permissionPageParam) throws Exception;

    Map<String, String> getUrlAndPermissionAll();

    List<Permission> getMenuList(Long id);
}
