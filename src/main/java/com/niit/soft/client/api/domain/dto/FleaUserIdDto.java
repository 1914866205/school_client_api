package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description 查询用户发布Dto
 * @Date 2020/6/11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaUserIdDto {
    /**
     * 用户id
     */
    private Long pkFleaUserId;
    private Integer currentPage;
    private Integer pageSize;
}
