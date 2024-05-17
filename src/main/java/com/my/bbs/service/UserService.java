package com.my.bbs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.bbs.entity.User;

/**
 * 用户表业务接口
 *
 * @author dinghao
 * @date 2021/3/16
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param rememberMe
     * @return
     */
    boolean doLogin(String username, String password, boolean rememberMe);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    boolean addUser(User user);
}
