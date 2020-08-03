package com.dxx.rbac.model;

/**
 * 角色实体类
 * @author cp5
 * @date 2020年 07月30日 17:42:30
 */
public class Role {
    private String id;
    private String name;
    private String code;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
