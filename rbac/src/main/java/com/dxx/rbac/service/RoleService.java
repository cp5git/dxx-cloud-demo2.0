package com.dxx.rbac.service;

import com.dxx.rbac.model.Role;
import java.util.List;

public interface RoleService {
    public List<Role> getUserRoles(String userId);
}
