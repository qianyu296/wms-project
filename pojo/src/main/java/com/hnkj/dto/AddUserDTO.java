package com.hnkj.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class AddUserDTO {
    @Pattern(regexp = "^[0-9a-zA-Z]{6,12}$")
    private String username;
    @Pattern(regexp = "^[\\u4e00-\\u9fa5a-zA-Z0-9]{1,10}$\n")
    private String name;
    private String sex;
    @Pattern(regexp = "^1[0-9]{10}$")
    private String phone;
    private int age;

}
