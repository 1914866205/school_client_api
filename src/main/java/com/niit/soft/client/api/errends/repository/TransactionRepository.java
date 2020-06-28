package com.niit.soft.client.api.errends.repository;


import com.niit.soft.client.api.errends.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wl
 * @ClassNameTransactionRepository
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
