package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.ScheduleDto;

/**
 * @author Tao
 */
public interface SysCourseService {
    /**
     * 根据班级id 学期id、周次，查询出归属课表的课程
     *
     * @param scheduleDto
     * @return
     */
    ResponseResult findWeekCourseTableByScheduleDto(ScheduleDto scheduleDto);

    /**
     * 获取今日课程数据信息
     *
     * @return
     */
    ResponseResult findTodayCourseTable(Long classId);
}
