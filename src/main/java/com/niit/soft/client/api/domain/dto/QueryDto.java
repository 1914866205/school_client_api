package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName QueryDto
 * @Description TODO
 * @date 2020-06-15 10:33
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryDto {
    private Integer id;
    private String equalsString;
    private String keywords;
}
