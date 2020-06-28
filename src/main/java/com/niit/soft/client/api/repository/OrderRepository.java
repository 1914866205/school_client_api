package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.SysOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName OrderRepository
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/29
 **/
public interface OrderRepository extends JpaRepository<SysOrder, Long> {
    /**
     * 消费明细
     *
     * @param jobNumber
     * @return
     */
    List<SysOrder> findAllByJobNumber(String jobNumber);


}