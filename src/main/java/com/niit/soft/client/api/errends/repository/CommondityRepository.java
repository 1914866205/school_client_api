package com.niit.soft.client.api.errends.repository;


import com.niit.soft.client.api.errends.domain.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wl
 * @ClassNameCommondityRepository
 * @Description Jpa
 * @Date 2020/6/9
 * @Version 1.0
 */
public interface CommondityRepository extends JpaRepository<Commodity, Long> {
}
