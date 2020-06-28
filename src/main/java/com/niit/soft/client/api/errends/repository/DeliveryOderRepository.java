package com.niit.soft.client.api.errends.repository;


import com.niit.soft.client.api.errends.domain.model.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wl
 * @ClassNameDeliveryOderRepository
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
public interface DeliveryOderRepository extends JpaRepository<DeliveryOrder, Long> {
}
