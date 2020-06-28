package com.niit.soft.client.api.repository.schoolmate;

import com.niit.soft.client.api.domain.model.schoolmate.DynamicPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName DynamicPhoto
 * @Description 动态图片
 * @Date 2020/6/11  21:37
 * @Version 1.0
 **/
public interface DynamicPhotoRepository extends JpaRepository<DynamicPhoto, String> {
    /**
     * 根据动态资讯id获取其配图
     *
     * @param id
     * @return
     */
    @Query(value = "select picture from dynamic_photo as p where p.dynamic_id =?1 ", nativeQuery = true)
    List<String> findDistinctByDynamicId(String id);
}
