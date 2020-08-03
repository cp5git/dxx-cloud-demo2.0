package com.dxx.rbac.controller;

import com.alibaba.fastjson.JSONArray;
import com.dxx.common.bean.CommonResponse;
import com.dxx.common.contants.ResponseCode;
import com.dxx.rbac.model.Resource;
import com.dxx.rbac.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 资源管理resource
 * @author cp5
 * @date 2020年 07月31日 10:17:55
 */
@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/user/resources/{userId}")
    public CommonResponse getResources(@PathVariable("userId") String userId){
        List<Resource> resources=resourceService.getUserResources(userId);

        return new CommonResponse(ResponseCode.SUCCESS, JSONArray.toJSONString(resources));

    }
}
