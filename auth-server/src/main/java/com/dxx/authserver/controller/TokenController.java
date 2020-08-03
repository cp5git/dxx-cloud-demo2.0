package com.dxx.authserver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dxx.authserver.service.CallUserService;
import com.dxx.authserver.utils.JwtTokenUtil;
import com.dxx.common.bean.Authentication;
import com.dxx.common.contants.ResponseCode;
import com.dxx.common.bean.CommonResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Toekn Controller
 * @author cp5
 * @date 2020年 07月30日 11:50:39
 */
@RestController
public class TokenController {

    private Log log= LogFactory.getLog(this.getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CallUserService callUserService;

    /**
     * 生成空token
     * @return
     */
    @RequestMapping(value="/token")
    public CommonResponse token(){
        String token=jwtTokenUtil.createEmptyToken();
        log.debug("创建token:"+token);

        CommonResponse response=new CommonResponse(ResponseCode.SUCCESS,token);

        return response;
    }

    /**
     * 判断url访问权限
     * @param url 要访问的url
     * @param token 当前用户token，通过header获取
     * @return
     */
    @RequestMapping(value="/permission/status")
    public CommonResponse permissionStatus(@RequestParam(value = "url") String url, @RequestHeader(value="token",required = false) String token){

        try {

            Authentication auth=jwtTokenUtil.getAuth(token);
            CommonResponse response=callUserService.getResources(auth.getUserId());

            if(response.getCode()==ResponseCode.SUCCESS.getCode()){
                JSONArray array=JSONArray.parseArray(response.getBody());
                for(int i=0;i<array.size();i++){
                    JSONObject obj=array.getJSONObject(i);
                    if(url.equals(obj.getString("url"))){
                        return new CommonResponse(ResponseCode.SUCCESS,token);
                    }
                }
                return new CommonResponse(ResponseCode.FORBIDDEN,token);
            }else{
                return response;
            }

        } catch (ExpiredJwtException e) {
            log.debug("token过期:"+token);
            return new CommonResponse(ResponseCode.TOKEN_EXPIRED);
        } catch (SignatureException | MalformedJwtException e) {
            log.info("非法token:"+token);
            return new CommonResponse(ResponseCode.TOKEN_ILLEGAL);
        }
    }

    /**
     * token获取用户认证信息
     * @param token
     * @return
     */
    @RequestMapping(value="/auth/{token}")
    public CommonResponse getAuth(@PathVariable("token") String token){
        Authentication auth=null;

        try {
            auth=jwtTokenUtil.getAuth(token);

            return new CommonResponse(ResponseCode.SUCCESS,JSONObject.toJSONString(auth));
        } catch (ExpiredJwtException e) {
            log.debug("token过期:"+token);
            return new CommonResponse(ResponseCode.TOKEN_EXPIRED);
        } catch (SignatureException | MalformedJwtException e) {
            log.info("非法token:"+token);
            return new CommonResponse(ResponseCode.TOKEN_ILLEGAL);
        }

    }
}
