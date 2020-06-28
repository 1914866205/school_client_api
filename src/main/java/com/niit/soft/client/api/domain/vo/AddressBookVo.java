package com.niit.soft.client.api.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName AddressBookVo
 * @Description TODO
 * @date 2020-06-15 10:27
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressBookVo {
    /**
     * 用户id
     */
    private String pkUserAccountId;
    /**
     * 用户真实姓名
     */
    private String userName;
    /**
     * 用户手机号
     */
    private String phoneNumber;
    /**
     * 用户头像
     */
    private String avatar;
}
