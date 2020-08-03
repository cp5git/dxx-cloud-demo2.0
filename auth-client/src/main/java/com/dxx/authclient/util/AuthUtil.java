package com.dxx.authclient.util;

import com.dxx.common.bean.Authentication;

import java.util.List;

/**
 * 登录信息缓存类，使用线程变量缓存登录信息
 * cpw
 * 20200727
 */
public class AuthUtil {

    private static ThreadLocal<Authentication> localAuth=new ThreadLocal<Authentication>();

    public static void setAuth(Authentication auth){
        localAuth.set(auth);
    }

    public static Authentication getAuth(){
        return localAuth.get();
    }

    /**
     * 判断当前用户是否用户某个角色
     * @param roleCode
     * @return
     */
    public static boolean hasRole(String roleCode){
        List<String> roleCodes=localAuth.get().getRoleCodes();
        for(String rc:roleCodes){
            if(rc.equals(roleCode)){
                return true;
            }
        }
        return false;
    }

}
