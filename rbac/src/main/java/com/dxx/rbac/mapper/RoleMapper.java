package com.dxx.rbac.mapper;

import com.dxx.rbac.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色mapper
 * @author cp5
 * @date 2020年 07月30日 11:13:23
 */
@Mapper
@Repository
public interface RoleMapper {

    /**
     * 获取用户的角色列表
     * @param userId 用户ID
     * @return 角色列表
     */
    @Select("SELECT r.id,r.name,r.code,r.remark " +
            "FROM DXX_ROLE r,DXX_USER_ROLE ur " +
            "WHERE r.ID=ur.ROLE_ID and ur.USER_ID= #{userId} ")
    public List<Role> getRolesByUser(@Param("userId") String userId);
}
