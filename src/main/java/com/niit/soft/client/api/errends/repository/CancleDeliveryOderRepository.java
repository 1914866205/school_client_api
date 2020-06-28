package com.niit.soft.client.api.errends.repository;

import com.niit.soft.client.api.errends.domain.model.CancleDeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wl
 * @ClassNameCancleDeliveryOderRepository
 * @Description 取消订单模块
 * @Date 2020/6/10
 * @Version 1.0
 */
public interface CancleDeliveryOderRepository extends JpaRepository<CancleDeliveryOrder, Long> {
}
