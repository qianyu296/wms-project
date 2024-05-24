package com.hnkj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVO {
    private Integer id; // 账号id
    private String username; // 用户名
    private String name; // 编号
    private String token; // jwt秘钥
}
