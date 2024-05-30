package com.hnkj.controller;


import com.hnkj.dto.AddUserDTO;
import com.hnkj.dto.UserPageQueryDTO;
import com.hnkj.result.PageResult;
import com.hnkj.result.Result;
import com.hnkj.service.AdminManagerService;
import com.hnkj.service.UserService;
import com.hnkj.utils.JwtUtil;
import com.hnkj.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/wms/admin-manager")
public class AdminMangerController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AdminManagerService adminManagerService;
    @Autowired
    private UserService userService;
    /**
     * 超级管理员添加用户
     *
     *
     * */
    @PostMapping("/addUser")
    public Result<Object> addUser(@RequestBody AddUserDTO addUserDTO){
        return null;
    }
    /**
     * 超级管理员获取管理员信息
     *
     * */
    @GetMapping("/getManagerUser")
    public Result<PageResult> getManagerUser(UserPageQueryDTO userPageQueryDTO){
        // 首先判断token内的权限
        String token = stringRedisTemplate.opsForValue().get("token");
        Map<String, Object> claims = JwtUtil.parseToken(token);
        // 获取用户权限
        Integer id = (Integer) claims.get("id");
        UserInfoVO userInfo = userService.getUserInfo(id);
        Integer roleId = userInfo.getRoleId();
        // 判断用户权限是否为超级管理员，如果不是，那么返回错误信息
        if(roleId != 0){
            return Result.error("权限错误！");
        }
        // 如果是超级管理员权限，那么返回所有管理员信息
        PageResult managerUser = adminManagerService.getManagerUser(userPageQueryDTO);
        return Result.success(managerUser);
    }
}
