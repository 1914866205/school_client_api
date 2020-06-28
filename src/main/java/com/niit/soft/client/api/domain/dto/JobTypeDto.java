package com.niit.soft.client.api.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @author su
 * @className JobTypeDto
 * @Description TODO
 * @Date 2020/6/11
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobTypeDto {

    private String name;
    private Long parentId;

}
