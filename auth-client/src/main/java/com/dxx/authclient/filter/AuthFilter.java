package com.dxx.authclient.filter;

import com.alibaba.fastjson.JSONObject;
import com.dxx.authclient.service.CallAuthService;
import com.dxx.authclient.util.AuthUtil;
import com.dxx.common.bean.Authentication;
import com.dxx.common.bean.CommonResponse;
import com.dxx.common.contants.DxxContants;
import com.dxx.common.contants.ResponseCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 鉴权过滤器
 * @author cp5
 * @date 2020年 07月30日 11:50:39
 */
@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

    @Autowired
    private CallAuthService callAuthService;

    private static final String USER_REQUEST_TITLE="/u/";
    private static final String ADMIN_REQUEST_TITLE="/a/";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Expose-Headers", DxxContants.HEADER_TOKEN);

        String token=request.getHeader(DxxContants.HEADER_TOKEN);

        if(StringUtils.isEmpty(token)) {
            //获取空token
            CommonResponse commonResponse = callAuthService.newToken();
            if (commonResponse.getCode() == ResponseCode.SUCCESS.getCode()) {
                token=commonResponse.getBody();
                response.setHeader(DxxContants.HEADER_TOKEN,token);
            }
        }

        //获取认证信息
        CommonResponse commonResponse=callAuthService.getAuth(token);

        if(commonResponse.getCode()== ResponseCode.SUCCESS.getCode()){
            Authentication auth=JSONObject.parseObject(commonResponse.getBody(),Authentication.class);
            AuthUtil.setAuth(auth);

            //更新token
            response.setHeader(DxxContants.HEADER_TOKEN,auth.getToken());

        }else{
            response.getWriter().write(commonResponse.toString());
            return;

        }

        //校验权限
        String url=request.getRequestURI();

        //对user开头的请求进行权限验证
        if(url.startsWith(USER_REQUEST_TITLE)){
            //未登录
            if(StringUtils.isEmpty(AuthUtil.getAuth().getUserId())){
                commonResponse=new CommonResponse(ResponseCode.UNAUTHORIZED);
                response.getWriter().write(commonResponse.toString());
                return;
            }

            //没有用户角色
            if(!AuthUtil.hasRole(DxxContants.ROLE_USER_CODE)){
                commonResponse=new CommonResponse(ResponseCode.FORBIDDEN);
                response.getWriter().write(commonResponse.toString());
                return;
            }

            //判断是否有权限访问
            commonResponse=callAuthService.hasPermission(token,url);

            if(commonResponse.getCode()!=ResponseCode.SUCCESS.getCode()){
                commonResponse=new CommonResponse(ResponseCode.FORBIDDEN);
                response.getWriter().write(commonResponse.toString());
                return;
            }

        }

        //对admin开头的请求进行权限验证
        if(url.startsWith(ADMIN_REQUEST_TITLE)){
            //未登录
            if(StringUtils.isEmpty(AuthUtil.getAuth().getUserId())){
                commonResponse=new CommonResponse(ResponseCode.UNAUTHORIZED);
                response.getWriter().write(commonResponse.toString());
                return;
            }

            //没有用户角色
            if(!AuthUtil.hasRole(DxxContants.ROLE_ADMIN_CODE)){
                commonResponse=new CommonResponse(ResponseCode.FORBIDDEN);
                response.getWriter().write(commonResponse.toString());
                return;
            }

            //判断是否有权限访问
            commonResponse=callAuthService.hasPermission(token,url);
            if(commonResponse.getCode()!=ResponseCode.SUCCESS.getCode()){
                commonResponse=new CommonResponse(ResponseCode.FORBIDDEN);
                response.getWriter().write(commonResponse.toString());
                return;
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

}
