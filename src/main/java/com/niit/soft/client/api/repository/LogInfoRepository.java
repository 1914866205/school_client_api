package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.Loginfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/30 10:12
 * @Version 1.0
 **/
public interface LogInfoRepository extends JpaRepository<Loginfo, Long> {
}
