package com.dxx.rbac.service;

import com.dxx.rbac.model.User;

public interface UserService {

    public User findByAccount(String account);

    void testTransaction() throws Exception;
}
