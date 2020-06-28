package com.niit.soft.client.api.domain.dto.schoolmate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DynamicDto
 * @Description TODO
 * @Author xiaobinggan
 * @Date 2020/6/9 8:54 上午
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DynamicDto {
    private String title;
    private String content;
    private String type;
    private String userId;
}
