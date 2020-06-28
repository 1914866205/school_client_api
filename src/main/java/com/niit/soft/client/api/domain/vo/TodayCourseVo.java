package com.niit.soft.client.api.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName TodayCourseVo
 * @Description TODO
 * @date 2020-06-08 23:22
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodayCourseVo {
    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 科目背景颜色
     */
    private String backgroundColor;


    /**
     * 房间名称
     */
    private String roomName;

    /**
     * 楼栋名
     */
    private String towerName;


    /**
     * 上课时间
     */
    private Integer time;

}
