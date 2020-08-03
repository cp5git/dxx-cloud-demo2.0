package com.dxx.rbac.mapper;

import com.dxx.rbac.model.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 资源mapper
 * @author cp5
 * @date 2020年 07月30日 11:14:17
 */
@Mapper
@Repository
public interface ResourceMapper {

    /**
     * 获取用户的资源列表
     * @param userId 用户ID
     * @return 资源列表
     */
    @Select("SELECT distinct r.id,r.parent_id as parentId,r.type,r.name,r.url,r.icon " +
            "FROM DXX_USER_ROLE ur,DXX_ROLE_RESOURCE rr,DXX_RESOURCE r " +
            "WHERE ur.USER_ID= #{userId} and ur.ROLE_ID=rr.ROLE_ID and rr.RESOURCE_ID=r.ID ")
    public List<Resource> getUserResources(@Param("userId") String userId);
}
