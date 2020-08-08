package com.dxx.producer2.controller;

import com.dxx.authclient.util.AuthUtil;
import com.dxx.common.bean.CommonResponse;
import com.dxx.common.contants.ResponseCode;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer2Controller {

    private static final Logger logger = LoggerFactory.getLogger(Producer2Controller.class);

    @RequestMapping(value="/u/user/id",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation("获取用户ID接口")
    public CommonResponse userId(){
        return new CommonResponse(ResponseCode.SUCCESS, AuthUtil.getAuth().getUserId());
    }

    @RequestMapping(value="/u/test",method = {RequestMethod.GET,RequestMethod.POST})
    public CommonResponse uTest(){
        return new CommonResponse(ResponseCode.SUCCESS,AuthUtil.getAuth().getUserId());
    }

    @RequestMapping(value="/test",method = {RequestMethod.GET,RequestMethod.POST})
    public CommonResponse test(){

        logger.info("test log");

        return new CommonResponse(ResponseCode.SUCCESS,"producer 1");
    }
}
