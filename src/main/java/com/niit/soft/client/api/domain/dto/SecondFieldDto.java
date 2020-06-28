package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SecondFieldDto
 * @Description TODO
 * @date 2020-06-10 11:06
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecondFieldDto {
    private Object field1;
    private Object field2;
}
