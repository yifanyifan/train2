package com.metadata.train.sys.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metadata.train.entity.common.PageEntity;
import com.metadata.train.entity.sys.Role;
import com.metadata.train.sys.mapper.RoleMapper;
import com.metadata.train.sys.param.RolePageParam;
import com.metadata.train.sys.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务实现类
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveRole(Role role) throws Exception {
        return super.save(role);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRole(Role role) throws Exception {
        return super.updateById(role);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteRole(Long id) throws Exception {
        return super.removeById(id);
    }


    @Override
    public PageEntity<Role> getRolePageList(RolePageParam rolePageParam) throws Exception {
        Page<Role> page = new Page<>(rolePageParam.getPageIndex(), rolePageParam.getPageSize());
        LambdaQueryWrapper<Role> wrapper = getLambdaQueryWrapper(rolePageParam);
        IPage<Role> iPage = roleMapper.selectPage(page, wrapper);
        return new PageEntity<Role>(iPage);
    }

    @Override
    public List<Role> getRoleList(RolePageParam rolePageParam) throws Exception {
        LambdaQueryWrapper<Role> wrapper = getLambdaQueryWrapper(rolePageParam);
        List<Role> RoleList = roleMapper.selectList(wrapper);
        return RoleList;
    }

    @Override
    public List<Role> getRoleByUser(Long id) {
        return roleMapper.getRoleByUser(id);
    }

    private LambdaQueryWrapper<Role> getLambdaQueryWrapper(RolePageParam rolePageParam) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        if (ObjectUtil.isNotEmpty(rolePageParam.getId())) {
            wrapper.eq(Role::getId, rolePageParam.getId());
        }
        return wrapper;
    }

}
