package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName LoginAccountIncreased.java
 * @Description TODO
 * @createTime 2020年06月17日 22:57:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginAccountIncreased {
    //学号
    private String jobNumber;

    //QQ第三方的OpenID
    private String qqOpenId;
}
