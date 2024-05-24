package com.hnkj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private Integer id;
    private String username;
    private String name;
    private Integer age;
    // 1为男，2为女
    private Integer sex;
    private String phone;
    // 1为启用,0为禁用
    private Integer status;
}
