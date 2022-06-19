package com.metadata.train.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metadata.train.entity.common.PageEntity;
import com.metadata.train.entity.sys.Permission;
import com.metadata.train.entity.sys.Role;
import com.metadata.train.entity.sys.User;
import com.metadata.train.sys.mapper.PermissionMapper;
import com.metadata.train.sys.mapper.RoleMapper;
import com.metadata.train.sys.mapper.UserMapper;
import com.metadata.train.sys.param.UserPageParam;
import com.metadata.train.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveUser(User user) throws Exception {
        return super.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(User user) throws Exception {
        return super.updateById(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUser(Long id) throws Exception {
        return super.removeById(id);
    }


    @Override
    public PageEntity<User> getUserPageList(UserPageParam userPageParam) throws Exception {
        Page<User> page = new Page<>(userPageParam.getPageIndex(), userPageParam.getPageSize());
        LambdaQueryWrapper<User> wrapper = getLambdaQueryWrapper(userPageParam);
        IPage<User> iPage = userMapper.selectPage(page, wrapper);
        return new PageEntity<User>(iPage);
    }

    @Override
    public List<User> getUserList(UserPageParam userPageParam) throws Exception {
        LambdaQueryWrapper<User> wrapper = getLambdaQueryWrapper(userPageParam);
        List<User> UserList = userMapper.selectList(wrapper);
        return UserList;
    }

    @Override
    public User loadUserByUsername(String username) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (ObjectUtil.isNotEmpty(user)) {
            List<Role> roleList = roleMapper.getRoleByUser(user.getId());
            user.setRoleList(roleList);
            if (CollectionUtil.isNotEmpty(roleList)) {
                List<Permission> permissionList = permissionMapper.getPermissionByRole(roleList);
                List<String> authorityList = permissionList.stream().map(i -> i.getAuthority()).collect(Collectors.toList());
                user.setPermissionList(authorityList);
            }
            return user;
        }
        return null;
    }

    private LambdaQueryWrapper<User> getLambdaQueryWrapper(UserPageParam userPageParam) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        return wrapper;
    }

}
