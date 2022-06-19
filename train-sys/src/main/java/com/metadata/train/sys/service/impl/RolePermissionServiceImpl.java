package com.metadata.train.sys.service.impl;

import com.metadata.train.entity.sys.RolePermission;
import com.metadata.train.sys.mapper.RolePermissionMapper;
import com.metadata.train.sys.service.RolePermissionService;
import com.metadata.train.sys.param.RolePermissionPageParam;
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
 *  服务实现类
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Slf4j
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveRolePermission(RolePermission rolePermission) throws Exception {
        return super.save(rolePermission);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRolePermission(RolePermission rolePermission) throws Exception {
        return super.updateById(rolePermission);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteRolePermission(Long id) throws Exception {
        return super.removeById(id);
    }


    @Override
    public PageEntity<RolePermission> getRolePermissionPageList(RolePermissionPageParam rolePermissionPageParam) throws Exception {
        Page<RolePermission> page = new Page<>(rolePermissionPageParam.getPageIndex(), rolePermissionPageParam.getPageSize());
        LambdaQueryWrapper<RolePermission> wrapper = getLambdaQueryWrapper(rolePermissionPageParam);
        IPage<RolePermission> iPage = rolePermissionMapper.selectPage(page, wrapper);
        return new PageEntity<RolePermission>(iPage);
    }

    @Override
    public List<RolePermission> getRolePermissionList(RolePermissionPageParam rolePermissionPageParam) throws Exception {
        LambdaQueryWrapper<RolePermission> wrapper = getLambdaQueryWrapper(rolePermissionPageParam);
        List<RolePermission> RolePermissionList = rolePermissionMapper.selectList(wrapper);
        return RolePermissionList;
    }

    private LambdaQueryWrapper<RolePermission> getLambdaQueryWrapper(RolePermissionPageParam rolePermissionPageParam) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        return wrapper;
    }

}
