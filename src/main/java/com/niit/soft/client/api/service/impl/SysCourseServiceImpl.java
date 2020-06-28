package com.niit.soft.client.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.ScheduleDto;
import com.niit.soft.client.api.domain.model.Schedule;
import com.niit.soft.client.api.domain.model.SysCourse;
import com.niit.soft.client.api.domain.model.SysSemester;
import com.niit.soft.client.api.domain.vo.CourseVo;
import com.niit.soft.client.api.domain.vo.TodayCourseVo;
import com.niit.soft.client.api.repository.*;
import com.niit.soft.client.api.service.SysCourseService;
import com.niit.soft.client.api.util.DateTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysCourseServiceImpl
 * @Description TODO
 * @date 2020-05-30 15:54
 **/
@Service
@Slf4j
public class SysCourseServiceImpl implements SysCourseService {
    @Resource
    private SysCourseRepository sysCourseRepository;

    @Resource
    private ScheduleRepository scheduleRepository;

    @Resource
    private SysSemesterRepository sysSemesterRepository;

    @Resource
    private SysSubjectRepository sysSubjectRepository;

    @Resource
    private RoomRepository roomRepository;

    @Resource
    private UserAccountRepository userAccountRepository;


    /**
     * 根据学期id、周次、班级id进行课表数据的查询
     *
     * @param scheduleDto
     * @return
     */
    @Override
    public ResponseResult findWeekCourseTableByScheduleDto(ScheduleDto scheduleDto) {

        //通过clazzId、semesterId、weekId查询出符合Schedule的数据
        Schedule schedule = scheduleRepository.findScheduleByClazzIdAndSemesterIdAndWeek(
                scheduleDto.getClazzId(), scheduleDto.getSemesterId(), scheduleDto.getWeek());
        //通过课表id查询出课表数据
        List<SysCourse> sysCourseList = sysCourseRepository.getSysCourseByScheduleId(schedule.getPkSchoolTimetableId());
        return ResponseResult.success(createVo(sysCourseList));
    }

    /**
     * 获取今日课表数据
     *
     * @param classId
     * @return
     */
    @Override
    public ResponseResult findTodayCourseTable(Long classId) {
        SysSemester sysSemester = sysSemesterRepository.findSysSemesterByName(DateTest.getSemester());
        //通过clazzId、semesterId、weekId查询出符合Schedule的数据
        Schedule schedule = scheduleRepository.findScheduleByClazzIdAndSemesterIdAndWeek(
                classId, sysSemester.getPkSemesterId(), DateTest.getWeek(String.valueOf(sysSemester.getOpenSchoolTime())));
        //通过课表id查询出课表数据
        List<SysCourse> sysCourseList = sysCourseRepository.getSysCourseByScheduleId(schedule.getPkSchoolTimetableId());
        return ResponseResult.success(createTodayVo(sysCourseList));
    }


    /**
     * 获取此周的课程数据
     *
     * @param sysCourseList
     * @return
     */
    private List<CourseVo> createVo(List<SysCourse> sysCourseList) {
        List<CourseVo> list = new ArrayList<>(10);
        //根据科目id、房间id、教工号id 取出需要的数据
        sysCourseList.forEach((item) -> {
            CourseVo course = new CourseVo();
            //获取科目名称、科目图片、科目背景色
            List<Tuple> subjectTuple = sysSubjectRepository.selectSysSubjectById(item.getSubjectId());
            //设置科目名称
            course.setSubjectName(subjectTuple.get(0).get(0, String.class));
            //设置科目背景
            course.setBackgroundColor(subjectTuple.get(0).get(2, String.class));
            //设置科目封面
            course.setCover(subjectTuple.get(0).get(1, String.class));
            //设置房间名
            course.setTowerName(roomRepository.findTowerNameById(Long.valueOf(item.getRoomId())));
            //获取课程的上课周次
            course.setWeekDay(item.getWeekDay());
            //获取课程的上课时间段
            course.setTime(item.getTime());
            //获取教师真实姓名
            course.setTeacherName(userAccountRepository.findUserNameByUserJobNumber(item.getUserJobNumber()));
            //获取周次
            course.setWeekDuration(item.getWeekDuration());
            //获取房间名称
            course.setRoomName(roomRepository.findNameById(Long.valueOf(item.getRoomId())));
            list.add(course);
        });
        return list;
    }

    private List<TodayCourseVo> createTodayVo(List<SysCourse> sysCourseList) {
        List<TodayCourseVo> list = new ArrayList<>(10);
        //根据科目id、房间id、教工号id 取出需要的数据
        sysCourseList.forEach((item) -> {
            if (item.getWeekDay() == DateTest.getCurrentWeek()) {
                TodayCourseVo course = new TodayCourseVo();
                //获取科目名称、科目图片、科目背景色
                List<Tuple> subjectTuple = sysSubjectRepository.selectSysSubjectById(item.getSubjectId());
                //设置科目名称
                course.setSubjectName(subjectTuple.get(0).get(0, String.class));
                //设置科目背景
                course.setBackgroundColor(subjectTuple.get(0).get(2, String.class));
                //设置房间名
                course.setTowerName(roomRepository.findTowerNameById(Long.valueOf(item.getRoomId())));
                //获取房间名称
                course.setRoomName(roomRepository.findNameById(Long.valueOf(item.getRoomId())));
                //获取课程的上课时间段
                course.setTime(item.getTime());
                list.add(course);
            }
        });
        return list;
    }


}
