package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName DepositDto
 * @Description TODO
 * @date 2020-06-10 11:00
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositDto {
    private String cardNumber;
    private Double money;
}
