package com.hnkj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hnkj.dto.UserPageQueryDTO;
import com.hnkj.mapper.AdminManagerMapper;
import com.hnkj.result.PageResult;
import com.hnkj.service.AdminManagerService;
import com.hnkj.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminManagerServiceImpl implements AdminManagerService {
    @Autowired
    AdminManagerMapper adminManagerMapper;
    @Override
    public PageResult getManagerUser(UserPageQueryDTO userPageQueryDTO) {
        PageHelper.startPage(userPageQueryDTO.getPage(),userPageQueryDTO.getPageSize());
        Page<UserVO> managerUser = adminManagerMapper.getManagerUser();
        PageResult pageResult = new PageResult();
        pageResult.setTotal(managerUser.getTotal());
        pageResult.setRecords(managerUser.getResult());
        return pageResult;
    }
}
