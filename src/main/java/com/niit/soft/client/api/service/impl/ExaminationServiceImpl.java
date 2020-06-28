package com.niit.soft.client.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.soft.client.api.domain.model.Examination;
import com.niit.soft.client.api.domain.model.SysSubject;
import com.niit.soft.client.api.mapper.ExaminationMapper;
import com.niit.soft.client.api.mapper.SysSubjectMapper;
import com.niit.soft.client.api.repository.SysSubjectRepository;
import com.niit.soft.client.api.service.ExaminationService;
import com.niit.soft.client.api.util.TimeUtils;
import org.apache.commons.collections.map.HashedMap;
import com.niit.soft.client.api.domain.vo.ExaminationVo;
import com.niit.soft.client.api.repository.ExaminationRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/8
 * @Version 1.0
 */
@Service
public class ExaminationServiceImpl implements ExaminationService {
    @Resource
    private ExaminationMapper examinationMapper;
    @Resource
    private SysSubjectMapper sysSubjectMapper;

    @Override
    public List<Map<String, Object>> getExaminationBySemester(String jobNumber) {
        QueryWrapper<Examination> wrapper = new QueryWrapper<>();
        wrapper.select("semester").groupBy("semester").orderByDesc("gmt_create");
        List<Map<String, Object>> results = examinationMapper.selectMaps(wrapper);
        results.forEach(result -> {
            QueryWrapper<Examination> wrapper1 = new QueryWrapper<>();
            Map<String, Object> map = new HashedMap();
            map.put("semester", result.get("semester"));
            map.put("job_number", jobNumber);
            wrapper1.select("area, start_time,finish_time, subject_id, score")
                    .allEq(map)
                    .orderByDesc("start_time");
            List<Map<String, Object>> examinations = examinationMapper.selectMaps(wrapper1);
            examinations.forEach(examination -> {
                QueryWrapper<SysSubject> subjectWrapper = new QueryWrapper<>();
                subjectWrapper.eq("pk_subject_id", examination.get("subject_id"));
                SysSubject subject = sysSubjectMapper.selectOne(subjectWrapper);
                examination.put("subject_name", subject.getName());
                int endTime = TimeUtils.getExaminationStartStatus((Timestamp) examination.get("start_time"));
                examination.put("endTime", endTime);
            });
            result.put("examinations", examinations);
        });
        return results;
    }
}
