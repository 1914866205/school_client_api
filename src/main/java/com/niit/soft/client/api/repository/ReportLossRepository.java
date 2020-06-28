package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.ReportLoss;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ReportLossRepository
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/1
 **/
public interface ReportLossRepository extends JpaRepository<ReportLoss, Long> {
    /**
     * 修改状态
     *
     * @param pkReportLossId
     * @param lossStatus
     * @return
     */
    @Modifying
    @LastModifiedBy
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update report_loss set loss_status = ?2 where pk_report_loss_id = ?1", nativeQuery = true)
    int updateLossStatus(Long pkReportLossId, Boolean lossStatus);


    /**
     * 通过挂失卡号和密码查找挂失数据
     *
     * @param lossJobNumber
     * @param password
     * @return
     */

    @Query(value = "select * from first_smart_campus.report_loss where loss_job_number=?1 AND password=?2 AND is_deleted=false", nativeQuery = true)
    ReportLoss findReportLoss(String lossJobNumber, String password);

    /**
     * 逻辑删除挂失
     *
     * @param pkReportLossId
     * @return
     */
    @Modifying
    @LastModifiedBy
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update report_loss set is_deleted = true where pk_report_loss_id = ?1", nativeQuery = true)
    int deletedReportLoss(Long pkReportLossId);
}