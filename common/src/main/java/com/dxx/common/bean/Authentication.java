package com.dxx.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 认证信息实体类
 * @author cp5
 * @date 2020年 07月30日 11:50:39
 */
public class Authentication implements Serializable {
    /**
     * token
     */
    private String token;
    /**
     * tokenId
     */
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 角色编码列表
     */
    private List<String> roleCodes=new ArrayList<String>();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public List<String> getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(List<String> roleCodes) {
        this.roleCodes = roleCodes;
    }
}
