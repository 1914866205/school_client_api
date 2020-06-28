package com.niit.soft.client.api.domain.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName LoginAccount.java
 * @Description TODO
 * @createTime 2020年06月17日 14:21:00
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkLoginAccountId;

//    关于 unique的坑:如果String类型不给长度，则不生效
//    long类型不给长度也生效

    //学号     此处因为修改不可行
    /**
     * 修改＝添加新数据+删除旧数据
     * 设置jobNumber可重复，openid不可重复
     * 添加时两个参数
     * 删除时根据openid查找，然后删除
     */
    @Column(nullable = false,unique = true,length = 32)
    private String jobNumber;

    //标准32位
    //QQ第三方的OpenID
    @Column(nullable = false, unique = true, length = 32)
    private String qqOpenId;
}
