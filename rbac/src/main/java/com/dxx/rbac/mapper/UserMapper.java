package com.dxx.rbac.mapper;

import com.dxx.rbac.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 用户mapper
 * @author cp5
 * @date 2020年 07月30日 11:13:23
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 根据用户帐号获取用户
     * @param account 用户帐号
     * @return 用户信息
     */
    @Select("SELECT id,account,name,password FROM DXX_USER WHERE account = #{account}")
    public User findByAccount(@Param("account") String account);

    @Update("update DXX_USER set name=#{name} where id=#{id} ")
    public void updateUserName(@Param("name") String name,@Param("id") String id);
}
