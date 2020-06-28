package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.InfoManage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName InfoManageRepository
 * @Description 资讯Repository
 * @Date 2020/5/26  11:03
 * @Version 1.0
 **/
public interface InfoManageRepository extends JpaRepository<InfoManage, Long> {


    /**
     * 查询所有咨询（不置顶的咨询）
     *
     * @param pageable
     * @return List<InfoManage>
     */
    @Query(value = "select * from first_smart_campus.info_manage where is_top = 0", nativeQuery = true)
    Page<InfoManage> getAllManage(Pageable pageable);

//

    /**
     * 更具id差资讯
     *
     * @param id
     * @return
     */
    InfoManage getInfoManageByPkInfoManageId(Long id);

    /**
     * 查询置顶咨讯
     *
     * @param pageable
     * @return
     */
    @Query(value = "select * from first_smart_campus.info_manage where is_top = 1", nativeQuery = true)
    Page<InfoManage> getIsTopInfo(Pageable pageable);

    /**
     * 更具资讯分类id查询资讯
     *
     * @param id
     * @return InfoManage
     */
    @Query(value = "select M  from InfoMangeType MT, InfoManage M , InfoType T " +
            "where MT.infoId = M.pkInfoManageId " +
            "and MT.typeId = T.pkInfoTypeId " +
            "and MT.typeId = ?1")
    List<InfoManage> getInfoByType(Long id);
}
