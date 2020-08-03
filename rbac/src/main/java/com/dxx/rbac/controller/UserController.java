package com.dxx.rbac.controller;

import com.alibaba.fastjson.JSONObject;
import com.dxx.common.bean.CommonResponse;
import com.dxx.common.contants.ResponseCode;
import com.dxx.rbac.model.Role;
import com.dxx.rbac.model.User;
import com.dxx.rbac.service.RoleService;
import com.dxx.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户Controller
 * @author cp5
 * @date 2020年 07月30日 11:50:39
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 根据登录帐号获取用户信息
     * 涉及敏感信息，注意该方法不能暴露到用户端
     * @param username
     * @return
     */
    @RequestMapping("/security/user/{account}")
    public CommonResponse findByAccount(@PathVariable("account") String username){
        User user=userService.findByAccount(username);

        if(user==null){
            return new CommonResponse(ResponseCode.USER_OR_PASSWORD_ERROR);
        }else{
            List<Role> roles=roleService.getUserRoles(user.getId());
            JSONObject json=new JSONObject();
            json.put("user",user);
            json.put("roles",roles);

            return new CommonResponse(ResponseCode.SUCCESS,json.toJSONString());
        }
    }



}
