package com.niit.soft.client.api.domain.dto.schoolmate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SchoolmateUserPageDto
 * @Description TODO
 * @Author xiaobinggan
 * @Date 2020/6/22 10:29 上午
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolmateUserPageDto {
    private String id;
    private int currentPage;
    private int pageSize;
}