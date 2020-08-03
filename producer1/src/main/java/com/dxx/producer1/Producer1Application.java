package com.dxx.producer1;

import com.dxx.authclient.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ServletComponentScan
//开启鉴权
@EnableAuthClient
public class Producer1Application {

    public static void main(String[] args) {
        SpringApplication.run(Producer1Application.class, args);
    }


}
