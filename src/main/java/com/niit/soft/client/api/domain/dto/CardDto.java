package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CardDto
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/10
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDto {
    private Long pkCardId;
    private Long id;
    private String jobNumber;
    private String cardNumber;
    private Double money;
    private Boolean Status;


}