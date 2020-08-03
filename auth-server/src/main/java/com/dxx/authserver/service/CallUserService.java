package com.dxx.authserver.service;

import com.dxx.common.bean.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="rbac")
@Repository
public interface CallUserService {

    /**
     * 获取用户信息
     * @param account
     * @return
     */
    @RequestMapping(value = "/security/user/{account}")
    public CommonResponse getUser(@PathVariable(value = "account") String account);

    /**
     * 获取用户访问权限
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/resources/{userId}")
    public CommonResponse getResources(@PathVariable(value = "userId") String userId);
}
