package com.metadata.train.sys.service;

import com.metadata.train.common.ResultEntity;
import com.metadata.train.entity.sys.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 服务类
 *
 * @author 易樊
 * @since 2022-04-07
 */
public interface LoginService {
    ResultEntity login(String username, String password, HttpServletRequest request) throws Exception;

    User getCurrentUser(HttpServletRequest request);
}
