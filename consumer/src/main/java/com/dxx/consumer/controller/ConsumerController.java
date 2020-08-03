package com.dxx.consumer.controller;

import com.dxx.consumer.service.CallProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cp5
 * @date 2020年 08月02日 22:19:02
 */
@RestController
public class ConsumerController {

    @Autowired
    private CallProducerService callProducerService;

    @RequestMapping("/call/producer")
    public String callProducer(){
        return callProducerService.test();
    }
}
