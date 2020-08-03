package com.dxx.rbac.service;

import com.dxx.rbac.model.Resource;
import java.util.List;

public interface ResourceService {

    public List<Resource> getUserResources(String userId);
}
