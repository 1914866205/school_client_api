package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tao
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    /**
     * 根据班级id、学期id、周次查询出课表id
     *
     * @param clazzId
     * @param semesterId
     * @param week
     * @return
     */
    Schedule findScheduleByClazzIdAndSemesterIdAndWeek(Long clazzId, Long semesterId, Integer week);


}

