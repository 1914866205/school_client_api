package com.niit.soft.client.api.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SubjectDto
 * @Description TODO
 * @date 2020-05-30 21:49
 **/
@Data
@Builder
public class SubjectDto {
    private String name;
    private String cover;
    private String backgroundColor;
}
