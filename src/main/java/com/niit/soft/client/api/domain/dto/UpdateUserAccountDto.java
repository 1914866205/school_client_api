package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UpdateUserAccountDto
 * @Description TODO
 * @date 2020-06-17 8:11
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserAccountDto {
    private String pkUserAccountId;
    private String avatar;
    private String nickname;
    private String gender;
    private String address;
}
