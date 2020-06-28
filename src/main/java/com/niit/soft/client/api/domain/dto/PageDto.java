package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName PageDto
 * @Description TODO
 * @date 2020-05-26 15:55
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
    private Object field;
    private int currentPage;
    private int pageSize;

    public PageDto(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}
