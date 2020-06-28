package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.SysSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import javax.persistence.Tuple;
import java.util.List;

/**
 * @author Tao
 */
public interface SysSubjectRepository extends JpaRepository<SysSubject, Long> {
    /**
     * 根据科目id查询科目的名称、图片、科目背景色
     *
     * @param pkSubjectId
     * @return
     */
    @Query(value = "SELECT name,cover,backgroundColor FROM SysSubject WHERE pkSubjectId=?1")
    List<Tuple> selectSysSubjectById(Long pkSubjectId);


    /**
     * 根据科目id查询科目
     *
     * @param id
     * @return
     */
    SysSubject findSysSubjectByPkSubjectId(Long id);

}
