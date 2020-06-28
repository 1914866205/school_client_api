package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ClazzRepository
 * @Description TODO
 * @date 2020-05-30 8:59
 **/
public interface ClazzRepository extends JpaRepository<Clazz, Long> {
    /**
     * 根据班级id查询班级表数据
     *
     * @param id
     * @return
     */
    Clazz findClazzByPkClazzId(Long id);

    /**
     * 根据id查询出班级名称
     *
     * @param pkClazzId
     * @return
     */
    @Query(value = "select name from clazz as c where c.pk_clazz_id=?1", nativeQuery = true)
    String findClazzById(Long pkClazzId);
}
