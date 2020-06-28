package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;


/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/26 8:30
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    //账号
    @Size(max = 32)
    private String userAccount;
    //密码
    @Size(max = 32)
    private String password;
}
