package com.hnkj.controller;

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
    @GetMapping("/getRole")
    public Result<Object> getRole(){
        // 从redis中获取token并解密
        String token = stringRedisTemplate.opsForValue().get("token");
        Map<String,Object> claims = JwtUtil.parseToken(token);
        Object roleId = claims.get("userRole");
        return Result.success(roleId);
    }

    @GetMapping("/getUserInfo/{userId}")
    public Result<UserInfoVO> getUserInfo(@PathVariable Integer userId){
        UserInfoVO userInfo = userService.getUserInfo(userId);
        return Result.success(userInfo);
    }

}
