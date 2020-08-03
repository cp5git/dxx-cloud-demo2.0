package com.dxx.authserver.utils;

import com.dxx.common.bean.Authentication;
import com.dxx.common.utils.DateUtil;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

/**
 * jwt工具类
 * @author cp5
 * @date 2020年 07月30日 11:50:39
 */
@Component
public class JwtTokenUtil {

    @Value("${jwt-token.key}")
    private String key="dxx123456";

    @Value("${jwt-token.expiration-time}")
    private int expirationTime=360;

    /**
     * 角色的KEY
     */
    private static final String JWT_KEY_ROLES="ROLES";

    /**
     * 是否已登录的KEY
     */
    private static final String JWT_KEY_LOGIN="LOGIN";

    /**
     * 已登录
     */
    private static final String JWT_LOGIN_YES="1";

    /**
     * 未登录
     */
    private static final String JWT_LOGIN_NO="0";

    /**
     * 用户ID的KEY
     */
    private static final String JWT_KEY_USER_ID="USER_ID";

    /**
     * 部门ID的KEY
     */
    private static final String JWT_KEY_DEPT_ID="JWT_KEY_DEPT_ID";

    private Claims getClaimsFromToken(String token)
    {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
            claims = null;
        }
        return claims;
    }

    /**
     * 创建空白的token
     * @return
     */
    public String createEmptyToken() {

        Claims claims = Jwts.claims();

        //登录状态-未登录
        claims.put(JWT_KEY_LOGIN,JWT_LOGIN_NO);

        String token = newToken(claims);

        return token;
    }


    /**
     * 创建已登录的Token
     * @param auth 登录信息
     * @return
     */
    public String createAuthToken(Authentication auth) {
        Claims claims = Jwts.claims();

        //登录状态-已登录
        claims.put(JWT_KEY_LOGIN,JWT_LOGIN_YES);
        claims.put(JWT_KEY_USER_ID,auth.getUserId());
        claims.put(JWT_KEY_DEPT_ID,auth.getDeptId());
        claims.put(JWT_KEY_ROLES,auth.getRoleCodes());

        String token = newToken(claims);

        return token;
    }

    /**
     * 刷新token,写入认证信息
     * @param token 旧token
     * @param auth 登录信息
     * @return 新token
     */
    public String refreshAuthToken(String token, Authentication auth) {
        Claims claims = getClaims(token);

        //登录状态-已登录
        claims.put(JWT_KEY_LOGIN,JWT_LOGIN_YES);
        claims.put(JWT_KEY_USER_ID,auth.getUserId());
        claims.put(JWT_KEY_DEPT_ID,auth.getDeptId());
        claims.put(JWT_KEY_ROLES,auth.getRoleCodes());

        token = refreshToken(claims);

        return token;
    }

    private Claims getClaims(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    private String newToken(Claims claims){
        String token = Jwts.builder()
                .setClaims(claims)
                .setId(System.currentTimeMillis()+"")
                .setIssuer("dxx")
                .setIssuedAt(new Date())
                .setExpiration(DateUtil.add(DateUtil.MINUTE,new Date(),expirationTime))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return token;
    }

    private String refreshToken(Claims claims){
        String token = Jwts.builder()
                .setClaims(claims)
                .setId(claims.getId())
                .setIssuer(claims.getIssuer())
                .setIssuedAt(claims.getIssuedAt())
                .setExpiration(DateUtil.add(DateUtil.MINUTE,new Date(),expirationTime))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return token;
    }

    /**
     * 获取认证信息
     * @param token
     * @return
     */
    public Authentication getAuth(String token) {
        Authentication auth=new Authentication();

        Claims claims = getClaims(token);

        auth.setId(claims.getId());
        auth.setUserId((String)claims.get(JWT_KEY_USER_ID));
        auth.setRoleCodes((List<String>)claims.get(JWT_KEY_ROLES));
        auth.setDeptId((String)claims.get(JWT_KEY_DEPT_ID));

        //刷新token
        token = refreshToken(claims);
        auth.setToken(token);

        return auth;
    }
}
