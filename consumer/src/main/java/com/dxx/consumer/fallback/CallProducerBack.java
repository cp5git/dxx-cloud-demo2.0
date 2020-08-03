package com.dxx.consumer.fallback;

import com.dxx.consumer.service.CallProducerService;
import org.springframework.stereotype.Component;

/**
 * @author cp5
 * @date 2020年 08月02日 22:33:37
 */
@Component
public class CallProducerBack implements CallProducerService {
    @Override
    public String test() {
        return "调用prodcuer失败";
    }
}
