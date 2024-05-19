package com.hnkj.service.impl;

import com.hnkj.dto.UserLoginDTO;
import com.hnkj.entity.User;
import com.hnkj.mapper.UserMapper;
import com.hnkj.service.UserService;
import com.hnkj.utils.JwtUtil;
import com.hnkj.utils.ThreadLocalUtil;
import com.hnkj.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    @Override
    public UserLoginVO userLogin(User user) {
        // 生成一个jwt密钥
        String jwt = "";
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("username",user.getUsername());
        claims.put("userRole",userMapper.getUserRole(user.getId()));
        jwt = JwtUtil.genToken(claims);
        // 将jwt密钥存入VO对象当中并返回
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setId(user.getId());
        userLoginVO.setToken(jwt);
        userLoginVO.setUsername(user.getUsername());
        userLoginVO.setNo(user.getNo());
        // 将token放在redis中存放
        stringRedisTemplate.opsForValue().set("token",jwt);
        ThreadLocalUtil.set(claims);
        return userLoginVO;
    }

}
