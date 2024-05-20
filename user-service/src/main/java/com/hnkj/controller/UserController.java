package com.hnkj.controller;

import com.hnkj.dto.AddUserDTO;
import com.hnkj.dto.ModifyPasswordDTO;
import com.hnkj.dto.UserLoginDTO;
import com.hnkj.entity.User;
import com.hnkj.result.Result;
import com.hnkj.service.UserService;
import com.hnkj.utils.JwtUtil;
import com.hnkj.utils.Md5Util;
import com.hnkj.utils.ThreadLocalUtil;
import com.hnkj.vo.UserInfoVO;
import com.hnkj.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/wms/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     *
     * */
    @PostMapping("/login")
    public Result<UserLoginVO> userLogin(@RequestBody UserLoginDTO userLoginDTO){
        // 首先判断DTO中的username是否存在
        User user = userService.getUser(userLoginDTO.getUsername());
        if(user == null){
            return Result.error("账号不存在");
        }
        // 如果username存在，那么判断账号密码是否正确
        boolean b = Md5Util.checkPassword(userLoginDTO.getPassword(),user.getPassword());
        if(b){
            UserLoginVO userLoginVO = userService.userLogin(user);
            return Result.success(userLoginVO);
        }else{
            return Result.error("密码错误");
        }
    }
    /**
     * 根据userId获取用户基本信息
     * @param userId
     * @return
     *
     * */
    @GetMapping("/getUserInfo/{userId}")
    public Result<UserInfoVO> getUserInfo(@PathVariable Integer userId){
        UserInfoVO userInfo = userService.getUserInfo(userId);
        return Result.success(userInfo);
    }
    /**
     * 修改密码
     * @param modifyPasswordDTO
     * @return
     *
     * */
    @PutMapping("/modifyPassword")
    public Result<Object> modifyPassword(@RequestBody ModifyPasswordDTO modifyPasswordDTO){
        // 首先对用户输入的旧密码与新密码进行一个判断，如果不一致那么直接返回修改失败了
        String oldPassword = modifyPasswordDTO.getOldPassword();
        String newPassword = modifyPasswordDTO.getNewPassword();
        if(!oldPassword.equals(newPassword)){
            return Result.error("两次密码输入不一致！");
        }
        // 获取用户
        User userInfo = userService.getUser(modifyPasswordDTO.getUsername());
        // 判断用户输入的旧密码是否正确
        if(!Md5Util.getMD5String(oldPassword).equals(userInfo.getPassword())){
            return Result.error("旧密码输入错误！");
        }
        Integer i = userService.modifyPassword(modifyPasswordDTO);
        if(i == 0){
            return Result.error("修改失败，系统错误！");
        }
        return Result.success("修改成功！");
    }
    /**
     * 超级管理员添加用户
     *
     *
     * */
    @PostMapping("/addUser")
    public Result<Object> addUser(@RequestBody AddUserDTO addUserDTO){
        return null;
    }
}
