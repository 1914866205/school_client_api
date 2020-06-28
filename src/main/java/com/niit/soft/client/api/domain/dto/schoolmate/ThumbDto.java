package com.niit.soft.client.api.domain.dto.schoolmate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ThumbDto
 * @Description TODO
 * @Author xiaobinggan
 * @Date 2020/6/9 11:06 上午
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThumbDto {
    private String pkThumbId;
    private String userId;
    private String dynamicId;
}

