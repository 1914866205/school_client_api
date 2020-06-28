package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaSearchDto.java
 * @Description TODO
 * @createTime 2020年06月09日 14:24:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaSearchDto {
    private String content;
    private int currentPage;
    private int pageSize;
}
