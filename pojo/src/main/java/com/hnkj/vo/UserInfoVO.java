package com.hnkj.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {
    private Integer id;
    private String username;
    private String name;
    private Integer age;
    private Integer sex;
    private String phone;
    private Integer roleId;
}
