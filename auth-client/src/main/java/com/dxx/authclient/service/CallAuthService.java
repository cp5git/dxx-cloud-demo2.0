package com.dxx.authclient.service;

import com.dxx.common.bean.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="auth-server")
@Repository
public interface CallAuthService {

    /**
     * 获取新的token
     * @return
     */
    @RequestMapping(value = "/token")
    public CommonResponse newToken();

    /**
     * 刷新token
     * @param token 旧token
     * @return 新token
     */
    @RequestMapping(value = "/token/{token}")
    public CommonResponse refreshToken(@PathVariable(value = "token") String token);

    /**
     * 获取请求授权者状态
     * @param token 当前用户token
     * @param url 请求的url
     * @return
     */
    @RequestMapping("/permission/status")
    public CommonResponse hasPermission(@RequestHeader(value="token") String token, @RequestParam(value = "url") String url);

    @RequestMapping("/auth/{token}")
    public CommonResponse getAuth(@PathVariable(value = "token") String token);
}
