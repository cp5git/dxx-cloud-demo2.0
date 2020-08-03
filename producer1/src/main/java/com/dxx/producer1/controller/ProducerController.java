package com.dxx.producer1.controller;

import com.dxx.authclient.util.AuthUtil;
import com.dxx.common.bean.CommonResponse;
import com.dxx.common.contants.ResponseCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @RequestMapping("/u/user/id")
    public CommonResponse userId(){
        return new CommonResponse(ResponseCode.SUCCESS,AuthUtil.getAuth().getUserId());
    }

    @RequestMapping("/u/test")
    public CommonResponse uTest(){
        return new CommonResponse(ResponseCode.SUCCESS,AuthUtil.getAuth().getUserId());
    }

    @RequestMapping("/test")
    public CommonResponse test(){
        return new CommonResponse(ResponseCode.SUCCESS,"producer 1");
    }
}
