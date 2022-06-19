package com.metadata.train.sys.service;

import com.metadata.train.entity.sys.User;
import com.metadata.train.sys.param.UserPageParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metadata.train.entity.common.PageEntity;
import java.util.List;

/**
 *  服务类
 *
 * @author 易樊
 * @since 2022-04-07
 */
public interface UserService extends IService<User> {

    /**
     * 保存
     *
     * @param user
     * @return
     * @throws Exception
     */
    boolean saveUser(User user) throws Exception;

    /**
     * 修改
     *
     * @param user
     * @return
     * @throws Exception
     */
    boolean updateUser(User user) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteUser(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param userPageParam
     * @return
     * @throws Exception
     */
    PageEntity<User> getUserPageList(UserPageParam userPageParam) throws Exception;

    /**
     * 获取列表对象
     *
     * @param userPageParam
     * @return
     * @throws Exception
     */
    List<User> getUserList(UserPageParam userPageParam) throws Exception;

    User loadUserByUsername(String username);
}
