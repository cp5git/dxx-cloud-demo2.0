package com.dxx.producer1;

import com.dxx.authclient.EnableAuthClient;
import com.dxx.common.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients
//开启鉴权
@EnableAuthClient
@Import(SwaggerConfig.class)
public class Producer1Application {

    public static void main(String[] args) {
        SpringApplication.run(Producer1Application.class, args);
    }


}
