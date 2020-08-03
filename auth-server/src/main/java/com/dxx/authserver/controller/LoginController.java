package com.dxx.authserver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dxx.authserver.service.CallUserService;
import com.dxx.authserver.utils.JwtTokenUtil;
import com.dxx.common.bean.Authentication;
import com.dxx.common.contants.DxxContants;
import com.dxx.common.contants.ResponseCode;
import com.dxx.common.bean.CommonResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录Controller
 * @author cp5
 * @date 2020年 07月30日 11:50:39
 */
@RestController
public class LoginController {

    private Log log= LogFactory.getLog(this.getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CallUserService callUserService;

    /**
     * 用户登录
     * @param account 帐号
     * @param password 密码
     * @param token 前用户token，通过header获取，未空时将自动创建
     * @return
     */
    @RequestMapping(value="/login/{account}/{password}")
    public CommonResponse login(
            HttpServletResponse httpServletResponse,
            @PathVariable(value="account") String account,
            @PathVariable(value="password") String password,
            @RequestHeader(value="token",required = false) String token){

        //获取用户信息
        CommonResponse response=callUserService.getUser(account);
        if(response.getCode()==ResponseCode.SUCCESS.getCode()){

            JSONObject body=JSONObject.parseObject(response.getBody());
            String dbPassword=body.getJSONObject("user").getString("password");
            String userId=body.getJSONObject("user").getString("id");

            if(password.equals(dbPassword)){

                //生成认证信息
                Authentication auth=new Authentication();
                auth.setUserId(userId);

                JSONArray roles=body.getJSONArray("roles");
                List<String> roleCodes=new ArrayList<String>();
                for(int i=0;i<roles.size();i++){
                    roleCodes.add(roles.getJSONObject(i).getString("code"));
                }
                auth.setRoleCodes(roleCodes);

                if(StringUtils.isEmpty(token)){
                    //生成带认证信息的token
                    token = jwtTokenUtil.createAuthToken(auth);
                }else{
                    //刷新token，添加认证信息
                    token = jwtTokenUtil.refreshAuthToken(token,auth);
                }

                //token写入头部
                httpServletResponse.setHeader("Access-Control-Expose-Headers", DxxContants.HEADER_TOKEN);
                httpServletResponse.setHeader(DxxContants.HEADER_TOKEN,token);

                return new CommonResponse(ResponseCode.SUCCESS,token);
            }else{
                return new CommonResponse(ResponseCode.USER_OR_PASSWORD_ERROR);
            }

        }else{
            return response;
        }

    }
}
