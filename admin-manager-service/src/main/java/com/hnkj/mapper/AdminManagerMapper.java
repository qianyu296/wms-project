package com.hnkj.mapper;

import com.github.pagehelper.Page;
import com.hnkj.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminManagerMapper {
    @Select("select u.id, u.name, u.username, u.age, u.sex, u.phone, u.status, ur.role_id from user u join user_role ur on u.id = ur.user_id where ur.role_id != 2")
    Page<UserVO> getManagerUser();
}
