package com.dxx.producer1.controller;

import com.dxx.authclient.util.AuthUtil;
import com.dxx.common.bean.CommonResponse;
import com.dxx.common.contants.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "生产者接口")
public class ProducerController {

    private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @RequestMapping(value="/u/user/id",method = RequestMethod.GET)
    @ApiOperation("获取用户ID接口")
    public CommonResponse userId(){
        return new CommonResponse(ResponseCode.SUCCESS,AuthUtil.getAuth().getUserId());
    }

    @RequestMapping("/u/test")
    public CommonResponse uTest(){
        return new CommonResponse(ResponseCode.SUCCESS,AuthUtil.getAuth().getUserId());
    }

    @RequestMapping("/test")
    public CommonResponse test(){

        logger.info("test log");

        return new CommonResponse(ResponseCode.SUCCESS,"producer 1");
    }
}
