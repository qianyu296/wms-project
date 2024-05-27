package com.hnkj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserPageQueryDTO {
    private int page; // 页码
    private int pageSize; // 页面条数
}
