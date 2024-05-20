package com.hnkj.mapper;

import com.hnkj.entity.User;
import com.hnkj.vo.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select id,no,username from user where username = #{username} and status = 1")
    User getUser(String username);
    @Select("select menu_id from menu where role_id = #{roleId}")
    List<Integer> getUserMenu(Integer RoleId);
    @Select("select id,no,username,age,sex,phone from user where id = #{userId} and status = 1")
    UserInfoVO getUserInfo(Integer userId);

}
