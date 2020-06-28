package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.SysBorrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Tao
 */
public interface SysBorrowRepository extends JpaRepository<SysBorrow, Long> {
    /**
     * 根据学号查询借阅的图书的数据
     *
     * @param borrowUserNumber
     * @return
     */
    List<SysBorrow> findSysBorrowsByBorrowUserNumber(String borrowUserNumber);


    /**
     * 查询归还的借阅记录
     *
     * @param borrowUserNumber
     * @return
     */
    @Query(value = "select * from sys_borrow as b where b.borrow_user_number=?1 and is_returned = true", nativeQuery = true)
    List<SysBorrow> findReturnSysBorrows(String borrowUserNumber);

    @Query(value = "select * from sys_borrow as b where b.borrow_user_number=?1 and is_returned = false", nativeQuery = true)
    List<SysBorrow> findNoReturnSysBorrows(String borrowUserNumber);
}
