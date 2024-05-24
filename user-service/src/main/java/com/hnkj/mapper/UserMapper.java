package com.hnkj.mapper;

import com.hnkj.entity.User;
import com.hnkj.vo.UserInfoVO;
import com.hnkj.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select id,no,username,password from user where username = #{username} and status = 1")
    User getUser(String username);
    @Select("select menu_id from menu where role_id = #{roleId}")
    List<Integer> getUserMenu(Integer RoleId);
    @Select("select u.id, u.no, u.username, u.age, u.sex, u.phone, ur.role_id from user u join user_role ur on u.id = ur.user_id where u.id = #{userId}")
    UserInfoVO getUserInfo(Integer userId);
    @Select("select u.id, u.no, u.username, u.age, u.sex, u.phone, u.status, ur.role_id from user u join user_role ur on u.id = ur.user_id where ur.role_id = 1;")
    List<UserVO> getManagerUser();
}
