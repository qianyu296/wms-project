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
    private String no;
    private String username;
    private Integer age;
    private Integer sex;
    private String phone;
    private Integer roleId;
}
