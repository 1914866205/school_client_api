
package com.niit.soft.client.api.domain.dto;

import com.niit.soft.client.api.domain.model.SysBorrow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName BorrowDto
 * @Description TODO
 * @date 2020-06-09 14:20
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDto {
    private Integer borrowCount;
    private List<SysBorrow> sysBorrowReturnList = new ArrayList<>();
    private List<SysBorrow> sysBorrowNoReturnList = new ArrayList<>();
}
