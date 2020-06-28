package com.niit.soft.client.api.domain.vo;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.quartz.SimpleTrigger;

import java.util.Date;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-06-09 13:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardVo {
    private String title;
    private String description;
    private String imageUrl;
    private Date createTime;
    private String username;
    private String phoneNumber;


}
