package com.niit.soft.client.api.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ScheduleDto
 * @Description TODO
 * @date 2020-05-30 15:56
 **/
@Data
@Builder
public class ScheduleDto {
    /**
     * 班级id
     */
    private Long clazzId;

    /**
     * a
     * 学期id
     */
    private Long semesterId;

    /**
     * 周次
     */
    private Integer week;
}
