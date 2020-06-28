package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JudgeCollectionDto {

    private Long goodsId;
    private Long userId;
}
