package com.hnkj.service;

import com.hnkj.dto.ModifyPasswordDTO;
import com.hnkj.dto.UserPageQueryDTO;
import com.hnkj.entity.User;
import com.hnkj.result.PageResult;
import com.hnkj.vo.UserInfoVO;
import com.hnkj.vo.UserLoginVO;
import com.hnkj.vo.UserMenuVO;
import com.hnkj.vo.UserVO;

import java.util.List;

public interface UserService {
    User getUser(String username);

    UserLoginVO userLogin(User user);
    UserInfoVO getUserInfo(Integer userId);

    Integer modifyPassword(ModifyPasswordDTO modifyPasswordDTO);

    PageResult getManagerUser(UserPageQueryDTO userPageQueryDTO);

    List<UserMenuVO> getUserMenu(Integer userRole);
}
