package com.dxx.authclient;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * auth-client spring注入配置
 * cp5
 * 20200729
 */
@Configuration
//扫描bean
@ComponentScan(basePackages = "com.dxx.authclient")
//扫描webfilter
@ServletComponentScan(basePackages = "com.dxx.authclient")
//扫描feignClient
@EnableFeignClients(basePackages = "com.dxx.authclient")
public class AuthClientConfig {

}


