package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.PartJob;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Su
 * @className JobRepository
 * @Description TODO
 * @Date 2020/6/9 13:39
 * @Version 1.0
 **/
public interface JobRepository extends JpaRepository<PartJob, Long> {


}
