package com.niit.soft.client.api.errends.repository;


import com.niit.soft.client.api.errends.domain.model.ReviewForm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wl
 * @ClassNameReviewFormRepository
 * @Description TODO
 * @Date 2020/6/12
 * @Version 1.0
 */
public interface ReviewFormRepository extends JpaRepository<ReviewForm, Long> {
}
