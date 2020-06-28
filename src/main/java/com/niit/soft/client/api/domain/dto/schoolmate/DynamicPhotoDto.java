package com.niit.soft.client.api.domain.dto.schoolmate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DynamicPhotoDto
 * @Description TODO
 * @Author xiaobinggan
 * @Date 2020/6/16 1:38 下午
 * @Version 1.0
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DynamicPhotoDto {
    private String dynamicId;
    private String picture;
}
