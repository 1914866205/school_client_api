package com.niit.soft.client.api.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Timestamp;

/**
 * @author xunmi
 * @ClassName ExaminationDto
 * @Description 考务信息 Vo
 * @Date 2020/6/3
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExaminationVo {

    /**
     * 主键，
     */
    private Long pkExaminationId;

    /**
     * 学期
     */
    private String semester;

    /**
     * 监考老师
     */
    private String teacherName;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp finishTime;

    /**
     * 地点
     */
    private String area;

    /**
     * 试卷分数
     */
    private Integer score;

    /**
     * 考试类型
     */
    private String type;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp gmtCreate;

    /**
     * 科目名
     */
    private String subjectName;

    /**
     * 班级姓名
     */
    private String clazzName;

    /**
     * 成绩
     */
    private Double performance;

}