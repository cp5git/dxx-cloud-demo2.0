package com.dxx.rbac.service.impl;

import com.dxx.rbac.mapper.RoleMapper;
import com.dxx.rbac.model.Role;
import com.dxx.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色service实现类
 * @author cp5
 * @date 2020年 07月31日 10:31:11
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获取用户的角色列表
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<Role> getUserRoles(String userId){
        return roleMapper.getRolesByUser(userId);
    }
}
