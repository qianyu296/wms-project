package com.hnkj.service;

import com.hnkj.entity.User;
import com.hnkj.vo.UserInfoVO;
import com.hnkj.vo.UserLoginVO;

public interface UserService {
    User getUser(String username);

    UserLoginVO userLogin(User user);
    UserInfoVO getUserInfo(Integer userId);
}
