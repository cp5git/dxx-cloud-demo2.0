package com.dxx.rbac.service.impl;

import com.dxx.rbac.mapper.UserMapper;
import com.dxx.rbac.model.User;
import com.dxx.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户Service实现类
 * @author cp5
 * @date 2020年 07月30日 11:33:00
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户帐号获取用户
     * @param account 用户帐号
     * @return 用户信息
     */
    @Override
    public User findByAccount(String account) {

        return userMapper.findByAccount(account);
    }
}
