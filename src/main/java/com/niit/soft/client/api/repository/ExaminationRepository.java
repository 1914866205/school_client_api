package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 田震
 * @ClassName ExaminationRepository
 * @Description 考务模块 Repository 相关操作
 * @Date 2020/6/4
 * @Version 1.0
 **/
public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    /**
     * 查询出所有与考务的基本信息
     *
     * @param jobNumber 学号
     * @return
     */
    @Query(value = "SELECT exa.pk_examination_id, exa.semester, exa.teacher_name, exa.start_time, \n" +
            "exa.finish_time,exa.area, exa.score, exa.type, exa.gmt_create, \n" +
            "sub.name as subject_name, cl.name as clazz_name, se.performance\n" +
            "FROM examination exa\n" +
            "LEFT JOIN sys_subject sub\n" +
            "ON exa.subject_id = sub.pk_subject_id\n" +
            "LEFT JOIN clazz cl\n" +
            "ON exa.clazz_id = cl.pk_clazz_id\n" +
            "LEFT JOIN student_exam se\n" +
            "ON se.examination_id = exa.pk_examination_id\n" +
            "WHERE exa.pk_examination_id IN (\n" +
            "\tSELECT se.examination_id \n" +
            "\tFROM student_exam \n" +
            "\tWHERE se.user_job_number = ?1\n" +
            ")", nativeQuery = true)
    List<Object> selectAll(String jobNumber);


}
