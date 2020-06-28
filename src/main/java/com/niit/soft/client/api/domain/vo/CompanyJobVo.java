package com.niit.soft.client.api.domain.vo;

import com.niit.soft.client.api.domain.model.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author su
 * @className CompanyJobVo
 * @Description TODO
 * @Date 2020/6/12
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyJobVo {

    private Long pkJobId;
    private String name;
    private String boss;
    private String bossAvatar;
    private Integer min;
    private Integer max;
    private String experience;
    private String diploma;
    private JobType jobType;

}
