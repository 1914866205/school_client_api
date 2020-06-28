package com.niit.soft.client.api.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author su
 * @className PartJobVo
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartJobVo {

    private Long pkPartJobId;
    private String name;
    private String description;
    private String bossId;
    private String bossName;
    private String bossPhone;
    private String avatar;
    private String workplace;
    private String workingDate;
    private String workingTime;
    private BigDecimal pay;
    private String payType;
    private String jobType;
    private Integer number;
    private Integer have;
    private Boolean need;

}
