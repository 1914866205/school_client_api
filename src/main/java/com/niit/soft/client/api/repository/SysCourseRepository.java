package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.SysCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author Tao
 */
public interface SysCourseRepository extends JpaRepository<SysCourse, Long> {
    /**
     * 根据课表id查询数据
     *
     * @param timetableId
     * @return
     */
    @Query(value = "SELECT s.name,s.cover,s.background_color,c.subject_id,c.room_id,c.user_job_number,c.time,c.week_day " +
            "FROM first_smart_campus.sys_subject s " +
            "LEFT JOIN first_smart_campus.sys_course c " +
            "ON s.pk_subject_id = c.subject_id " +
            "WHERE c.schedule_id=?1", nativeQuery = true)
    List<Map<String, Object>> selectById(Long timetableId);

    /**
     * 根据scheduleId查询数据
     *
     * @param scheduleId
     * @return
     */
    List<SysCourse> getSysCourseByScheduleId(Long scheduleId);

}
