package com.dxx.consumer.service;

import com.dxx.consumer.fallback.CallProducerBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="producer",fallback = CallProducerBack.class)
@Repository
public interface CallProducerService {
    @RequestMapping("/test")
    public String test();

}
