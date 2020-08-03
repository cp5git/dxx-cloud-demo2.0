package com.dxx.common.contants;

/**
 * 返回值状态码枚举类
 * @author cp5
 * @date 2020年 07月30日 11:50:39
 */
public enum ResponseCode {
    SUCCESS(10200, "成功"),
    FAIL(10002, "失败"),
    TOKEN_ILLEGAL(10003, "token非法"),
    TOKEN_EXPIRED(10004, "token过期"),
    SERVER_DOWNGRADE(10005, "服务降级"),
    USER_OR_PASSWORD_ERROR(10006, "用户名或密码错误"),
    UNAUTHORIZED(10401, "用户未登录"),
    FORBIDDEN(10403, "没有权限访问");


    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
