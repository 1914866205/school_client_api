package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName AlipayDto
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/5
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlipayDto {
    private Double amount;
    private String jobNumber;
    private String rechargeType;
    private Integer regexId;
}