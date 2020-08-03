package com.dxx.authclient;

import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({AuthClientConfig.class})
/**
 * auth-client 注解
 * 在启动类加上@AuthClient启用auth服务认证
 * cp5
 * 20200729
 */
public @interface AuthClient {
}
