package com.hnkj.mapper;

import com.github.pagehelper.Page;
import com.hnkj.entity.User;
import com.hnkj.vo.UserInfoVO;
import com.hnkj.vo.UserMenuVO;
import com.hnkj.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface UserMapper {
    // Page<User> pageQuery(UserPageQueryDTO userPageQueryDTO);
    @Select("select id,name, username,password from user where username = #{username} and status = 1")
    User getUser(String username);
    @Select("select menu_id,description from menu where role_id = #{roleId}")
    List<UserMenuVO> getUserMenu(Integer roleId);
    @Select("select u.id, u.name, u.username, u.age, u.sex, u.phone, ur.role_id from user u join user_role ur on u.id = ur.user_id where u.id = #{userId}")
    UserInfoVO getUserInfo(Integer userId);
    @Select("select u.id, u.name, u.username, u.age, u.sex, u.phone, u.status, ur.role_id from user u join user_role ur on u.id = ur.user_id where ur.role_id = 1")
    Page<UserVO> getManagerUser();
}
