package com.niit.soft.client.api.mapper.schoolmate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niit.soft.client.api.domain.dto.schoolmate.DynamicCollectionDto;
import com.niit.soft.client.api.domain.model.schoolmate.Collections;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author Yujie_Zhao
 * @ClassName CollectionsMapper
 * @Description 收藏
 * @Date 2020/6/11  14:37
 * @Version 1.0
 **/
public interface CollectionsMapper extends BaseMapper<Collections> {

    /**
     * 根据用户id
     *
     * @param id
     * @return
     */
    @Select("SELECT d.content as content,d.gmt_create as gmtCreate, u.avatar as userAvatar,u.nickname as nickname " +
            "FROM collections c " +
            "LEFT JOIN dynamic d " +
            "ON d.pk_dynamic_id = c.dynamic_id " +
            "LEFT JOIN user_account u " +
            "ON d.user_id = u.pk_user_account_id " +
            "WHERE c.user_id = #{id}")
    List<Map<String, Object>> findCollectionsByUserId(String id);

    /**
     * 根据动态资讯id查询收藏的动态资讯
     *
     * @param id
     * @return
     */
    @Select("SELECT d.content as content,d.gmt_create as gmtCreate," +
            "u.avatar as userAvatar,u.nickname as nickname " +
            "FROM dynamic d " +
            "LEFT JOIN user_account u " +
            "ON d.user_id = u.pk_user_account_id " +
            "WHERE d.pk_dynamic_id = #{id}")
    DynamicCollectionDto findCollectionsByDynamicId(String id);

}
