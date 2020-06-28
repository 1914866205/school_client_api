package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.dto.TypeDto;
import com.niit.soft.client.api.domain.model.FleaType;
import com.niit.soft.client.api.domain.vo.FleaGoodsVo;
import com.niit.soft.client.api.domain.vo.GoodsVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaTypeRepository.java
 * @Description TODO
 * @createTime 2020年06月09日 13:55:00
 */
public interface FleaTypeRepository extends JpaRepository<FleaType, Long> {
    /**
     * 根据分类ID查询商品
     *
     * @param typeDto  TypeDto
     * @param pageable Pageable
     * @return List<FleaGoodsVo>
     */
    @Query(value = "select new com.niit.soft.client.api.domain.vo.FleaGoodsVo(g.pkFleaGoodsId,g.goodsName,g.goodsDescription,g.goodsImgUrl,g.goodsPrice,g.goodsMark," +
            "g.goodsCreateTime,t.pkFleaTypeId,t.typeName,u.pkFleaUserId,u.nickname,u.username,u.avatar,g.isDeleted) " +
            "from FleaGoods g " +
            "left join g.fleaType t " +
            "left join g.fleaUser u  where t.pkFleaTypeId =:#{#typeDto.getTypeId()} and g.isDeleted = false ")
    List<FleaGoodsVo> getGoodsByTypeId(Pageable pageable, TypeDto typeDto);
}
