package com.niit.soft.client.api.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName CourseVo
 * @Description TODO
 * @date 2020-06-04 9:46
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseVo {

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 科目背景颜色
     */
    private String backgroundColor;

    /**
     * 科目图片
     */
    private String cover;

    /**
     * 教师名称
     */
    private String teacherName;

    /**
     * 房间名称
     */
    private String roomName;

    /**
     * 楼栋名
     */
    private String towerName;

    /**
     * 周几
     */
    private Integer weekDay;

    /**
     * 上课时间
     */
    private Integer time;

    /**
     * 周次（例如 1-5，8-13）
     */
    private String weekDuration;
}
