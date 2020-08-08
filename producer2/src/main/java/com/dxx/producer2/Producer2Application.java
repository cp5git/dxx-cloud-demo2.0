package com.dxx.producer2;

import com.dxx.authclient.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//开启鉴权
@EnableAuthClient
public class Producer2Application {

    public static void main(String[] args) {
        SpringApplication.run(Producer2Application.class, args);
    }


}
