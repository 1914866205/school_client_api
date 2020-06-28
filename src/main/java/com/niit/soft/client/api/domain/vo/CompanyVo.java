package com.niit.soft.client.api.domain.vo;

import com.niit.soft.client.api.domain.model.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author su
 * @className CompanyVo
 * @Description TODO
 * @Date 2020/6/12
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyVo {


    private Long pkCompanyId;
    private String name;
    private String tag;
    private String logo;
    private Integer workers;
    private String type;
    private String description;
    private String workingTime;
    private String workingStatus;
    private String vacation;
    private String address;
    private String longitude;
    private String latitude;
    private Timestamp gmtCreate;
    private List<CompanyJobVo> companyJobVos;
}
