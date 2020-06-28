package com.niit.soft.client.api.mapper;

import com.niit.soft.client.api.domain.vo.DormVo;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Tao
 */
public interface TowerMapper {
    /**
     * 查询所有宿舍名
     *
     * @return
     * @throws SQLException
     */
    @Select("SELECT  t1.name as towerName,t2.id as roomId,t2.name as roomName,t3.name as towerUnitName " +
            "FROM tower t1 " +
            "LEFT JOIN room t2 " +
            "ON t1.pk_tower_id = t2.tower_id " +
            "LEFT JOIN tower_unit t3 " +
            "ON t2.unit_id = t3.unit_id " +
            "WHERE t1.type = 1 ")
    List<DormVo> getAllDormByType() throws SQLException;

}
