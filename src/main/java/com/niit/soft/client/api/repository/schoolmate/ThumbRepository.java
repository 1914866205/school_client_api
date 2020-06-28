package com.niit.soft.client.api.repository.schoolmate;

import com.niit.soft.client.api.domain.model.schoolmate.Thumb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yujie_Zhao
 * @ClassName ThumbRepository
 * @Description 点赞
 * @Date 2020/6/8  14:07
 * @Version 1.0
 **/
public interface ThumbRepository extends JpaRepository<Thumb, Long> {
    /**
     * 更具用户id，动态id删除点赞
     *
     * @param dynamicId
     * @param userId
     */
    @Transactional
    @Modifying
    @Query("delete from Thumb where dynamicId = ?1 and userId = ?2 ")
    void deleteByDynamicIdAndDynamicId(Long dynamicId, Long userId);
}
