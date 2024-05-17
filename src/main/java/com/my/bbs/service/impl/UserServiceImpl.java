package com.my.bbs.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.bbs.common.constant.CommonConstant;
import com.my.bbs.common.exception.BusinessException;
import com.my.bbs.dao.RoleMapper;
import com.my.bbs.dao.UserMapper;
import com.my.bbs.dao.UserRoleMapper;
import com.my.bbs.entity.Role;
import com.my.bbs.entity.User;
import com.my.bbs.entity.UserRole;
import com.my.bbs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户业务接口实现
 *
 * @author dinghao
 * @date 2021/3/16
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;

    private final RoleMapper roleMapper;

    @Override
    public boolean doLogin(String username, String password, boolean rememberMe) {
        System.out.println("111"+ 0111);
        User u = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (u == null) {
            throw new BusinessException("用户不存在");
        }
        if (!SaSecureUtil.sha256(password).equals(u.getPassword())) {
            throw new BusinessException("密码不正确");
        }
        StpUtil.login(u.getId(), rememberMe);
        return true;
    }

    @Override
    public boolean addUser(User user) {
        if (userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername())) != null) {
            throw new BusinessException("用户名已存在");
        }
        user.setPassword(SaSecureUtil.sha256(user.getPassword()));
        if (baseMapper.insert(user) <= 0) {
            throw new BusinessException("用户新增失败");
        }
        //给用户设置基本角色
        UserRole ur = new UserRole();
        ur.setUserId(user.getId());
        ur.setRoleId(roleMapper.selectOne(new LambdaQueryWrapper<Role>().eq(Role::getRoleCode, CommonConstant.ROLE_USER)).getId());
        if (userRoleMapper.insert(ur) <= 0) {
            throw new BusinessException("用户新增失败");
        }
        return true;
    }
}
