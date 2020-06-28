package com.niit.soft.client.api.service;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/8
 * @Version 1.0
 */
public interface ExaminationService {
    /**
     * 根据学期进行分类
     *
     * @return
     */
    List<Map<String, Object>> getExaminationBySemester(String jobNumber);
}
