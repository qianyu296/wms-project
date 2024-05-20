package com.hnkj.dto;

import lombok.Data;

@Data
public class ModifyPasswordDTO {
    private Integer userId;
    private String username;
    private String oldPassword;
    private String newPassword;
}
