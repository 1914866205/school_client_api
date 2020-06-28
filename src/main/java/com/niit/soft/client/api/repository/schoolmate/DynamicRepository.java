package com.niit.soft.client.api.repository.schoolmate;

import com.niit.soft.client.api.domain.model.schoolmate.Dynamic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yujie_Zhao
 * @ClassName DynamicRepository
 * @Description 动态
 * @Date 2020/6/9  9:08
 * @Version 1.0
 **/
public interface DynamicRepository extends JpaRepository<Dynamic, String> {
    /**
     * 根据id查找
     *
     * @param id
     * @return Dynamic
     */
    Dynamic findDynamicByPkDynamicId(String id);
}
