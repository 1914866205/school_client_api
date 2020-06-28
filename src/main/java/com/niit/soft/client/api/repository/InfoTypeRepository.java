package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.model.InfoManage;
import com.niit.soft.client.api.domain.model.InfoType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Yujie_Zhao
 * @ClassName InfoTypeRepository
 * @Description 资讯分类Repository
 * @Date 2020/5/26  17:16
 * @Version 1.0
 **/
public interface InfoTypeRepository extends JpaRepository<InfoType, Long>, JpaSpecificationExecutor<InfoType> {
    /**
     * 根据id查找分类
     *
     * @param id
     * @return InfoType
     */
    InfoType findByPkInfoTypeId(Long id);

    /**
     * 根据分离名称查找分类
     *
     * @param name
     * @return
     */
    InfoType findByName(String name);

    /**
     * @param id
     * @return
     */
    @Query(value = "select name,sort from first_smart_campus.info_type where pk_info_type_id = ?1", nativeQuery = true)
    InfoType getTopById(Long id);


    /**
     * 根据id查找分类
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT T.pk_info_type_id, T.name,T.sort,M.pk_info_manage_id  " +
            "FROM first_smart_campus.info_manage_type MT ," +
            " first_smart_campus.info_type T  ," +
            " first_smart_campus.info_manage M " +
            "WHERE MT.info_id = M.pk_info_manage_id " +
            "AND MT.type_id = T.pk_info_type_id " +
            "AND T.pk_info_type_id = ?1", nativeQuery = true)
    List<Map<String, Object>> findInfoByPkInfoTypeId(Long id);


    /**
     * @param typeId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Query(value = "select m " +
            "FROM InfoManage m ," +
            "InfoMangeType mt " +
            "WHERE mt.infoId = m.pkInfoManageId " +
            "AND mt.typeId = ?1 " +
            "AND m.isTop = false ")
    List<InfoManage> findInfoMangeByTypeId(Long typeId, Integer pageNumber, Integer pageSize);

    @Query(value = "select m " +
            "FROM InfoManage m ," +
            "InfoMangeType mt " +
            "WHERE mt.infoId = m.pkInfoManageId " +
            "AND mt.typeId = ?1 " +
            "AND m.isTop = false ")
    Page<InfoManage> findInfoMangeByPage(Long typeId, Pageable pageable);
}

