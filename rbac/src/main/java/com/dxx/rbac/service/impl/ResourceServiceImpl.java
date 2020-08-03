package com.dxx.rbac.service.impl;

import com.dxx.rbac.mapper.ResourceMapper;
import com.dxx.rbac.model.Resource;
import com.dxx.rbac.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 资源service实现类
 * @author cp5
 * @date 2020年 07月31日 10:19:51
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 获取用户的资源列表
     * @param userId 用户ID
     * @return 资源列表
     */
    @Override
    public List<Resource> getUserResources(String userId) {
        return resourceMapper.getUserResources(userId);
    }
}
