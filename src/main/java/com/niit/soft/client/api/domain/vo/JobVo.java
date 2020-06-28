package com.niit.soft.client.api.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.niit.soft.client.api.domain.model.Company;
import com.niit.soft.client.api.domain.model.Job;
import com.niit.soft.client.api.domain.model.JobType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author su
 * @className Job
 * @Description TODO
 * @Date 2020/6/12
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobVo {

    private Long pkJobId;
    private String name;
    private String description;
    private String boss;
    private String bossPhone;
    private Company company;
    private String workplace;
    private String workingTime;
    private BigDecimal pay;
    private Integer min;
    private Integer max;
    private String experience;
    private String diploma;
    private JobType jobType;
    private Integer number;
    private Timestamp gmtCreate;

}
