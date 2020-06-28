package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.SysSemester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Tao
 */
public interface SysSemesterRepository extends JpaRepository<SysSemester, Long> {
    /**
     * 查询所有学期数据
     *
     * @return
     */
    @Query("select s from SysSemester s")
    List<SysSemester> findAllSysSemester();


    /**
     * 根据学期name查询学期数据
     *
     * @param name
     * @return
     */
    @Query(value = "select s.* from first_smart_campus.sys_semester as s where s.name=?1", nativeQuery = true)
    SysSemester findSysSemesterByName(String name);

}

