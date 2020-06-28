package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.SysCard;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName CardRepository
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/26
 **/
public interface CardRepository extends JpaRepository<SysCard, Long> {
    /**
     * 自定义分页查询
     *
     * @param pageable
     * @return
     */
    @Query("select u from SysCard u")
    Page<SysCard> findALL(Pageable pageable);

    /**
     * 通过学号查询余额
     *
     * @param JobNumber
     * @return
     */
    @Query(value = "select card_balance from sys_card as u where u.job_number=?1 ", nativeQuery = true)
    Double findCardBalanceByJobNumber(String JobNumber);

    /**
     * 校园卡充值
     *
     * @param cardNumber
     * @param money
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update sys_card as u set  u.card_balance = u.card_balance + ?2 where u.card_number=?1",
            nativeQuery = true)
    int insertCardBalance(String cardNumber, Double money);

    /**
     * 电费充值
     *
     * @param id
     * @param money
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update room as u set  u.electricity_balance = u.electricity_balance + ?2 where u.id=?1",
            nativeQuery = true)
    int insertelectricityBalance(Long id, Double money);

    /**
     * 网费充值
     *
     * @param jobNumber
     * @param money
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update room as u set  u.electricity_balance = u.electricity_balance + ?2 where u.room_leader_job_number=?1",
            nativeQuery = true)
    int insertBalance(String jobNumber, Double money);

    /**
     * 状态激活
     *
     * @param pkCardId
     * @param status
     * @return
     */
    @Modifying
    @LastModifiedBy
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update sys_card set status = ?2 where pk_card_id = ?1", nativeQuery = true)
    int updateStatus(Long pkCardId, Boolean status);

    /**
     * 通过卡号和卡密查找一卡通数据
     *
     * @param cardNumber
     * @param cardPassword
     * @return
     */
    SysCard findSysCardByCardNumberAndCardPassword(String cardNumber, String cardPassword);


}