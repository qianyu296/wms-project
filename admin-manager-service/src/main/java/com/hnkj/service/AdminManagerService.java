package com.hnkj.service;


import com.hnkj.dto.UserPageQueryDTO;
import com.hnkj.result.PageResult;

public interface AdminManagerService {
    PageResult getManagerUser(UserPageQueryDTO userPageQueryDTO);
}
