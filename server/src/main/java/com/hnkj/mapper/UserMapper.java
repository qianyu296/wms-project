package com.hnkj.mapper;

import com.hnkj.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select id,no,username,password,age,sex,phone,status from user where username = #{username} and status = 1")
    User getUser(String username);
    @Select("select role_id from user_role where user_id = #{userId}")
    Integer getUserRole(Integer userId);
    @Select("select menu_id from menu where role_id = #{roleId}")
    List<Integer> getUserMenu(Integer RoleId);
}
