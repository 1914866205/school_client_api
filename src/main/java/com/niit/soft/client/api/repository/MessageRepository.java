package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Tao
 */
public interface MessageRepository extends JpaRepository<Message, Long> {


}
