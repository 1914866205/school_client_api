package com.niit.soft.client.api.domain.dto.schoolmate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName PageDto
 * @Description TODO
 * @Author xiaobinggan
 * @Date 2020/6/14 3:48 下午
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolmatePageDto {
    private int currentPage;
    private int pageSize;
}
