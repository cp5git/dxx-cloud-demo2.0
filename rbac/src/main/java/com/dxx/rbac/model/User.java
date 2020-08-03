package com.dxx.rbac.model;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户实体类
 * @author cp5
 * @date 2020年 07月30日 11:18:21
 */
public class User {
    private String id;
    private String account;
    private String name;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toJson(){
        return JSONObject.toJSONString(this);
    }
}
