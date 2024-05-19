package com.hnkj.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @JsonIgnore
    private Integer id;
    private String no;
    private String username;
    @JsonIgnore
    private String password;
    private Integer age;
    private Integer sex;
    private String phone;
    private Integer status;
}
