package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-06-10 14:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeDto {
    /**
     * 根据分类ID查询数据参数
     */
    private Long typeId;
    private Integer currentPage;
    private Integer pageSize;
}
