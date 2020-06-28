
package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.dto.*;
import com.niit.soft.client.api.domain.model.FleaCollection;
import com.niit.soft.client.api.domain.model.FleaUser;
import com.niit.soft.client.api.domain.vo.CollectionVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName a.java
 * @Description TODO
 * @createTime 2020年06月09日 11:26:00
 */
public interface FleaCollectionRepository extends JpaRepository<FleaCollection, Long> {
    /**
     * 根据用户id查询该用户的所有收藏
     *
     * @param userDto FleaUserIdDto
     * @return List<CollectionVo>
     */
    @Query(value = "select new com.niit.soft.client.api.domain.vo.CollectionVo(u.pkFleaUserId,g.pkFleaGoodsId,g.goodsName,g.goodsDescription,g.goodsPrice,g.goodsImgUrl,g.goodsMark,u.username,u.phoneNumber,c.createTime)" +
            "from FleaCollection c " +
            "left join c.fleaGoods g " +
            "left join c.fleaUser u where u.pkFleaUserId =:#{#userDto.pkFleaUserId} and c.isDeleted = false ")
    List<CollectionVo> getCollection(FleaUserIdDto userDto);

    /**
     * 根据用户ID以及商品ID逻辑删除某个收藏
     *
     * @param collectionDto CancelCollectionDto
     * @return int
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "delete from FleaCollection  where fleaGoods.pkFleaGoodsId = :#{#collectionDto.getGoodsId()} and fleaUser.pkFleaUserId = :#{#collectionDto.getUserId()}")
    int logicalDel(CancelCollectionDto collectionDto);

    /**
     * 判断商品是否被该用户收藏
     *
     * @param judgeCollectionDto JudgeCollectionDto
     * @return List<CollectionVo>
     */
    @Query(value = "select new com.niit.soft.client.api.domain.vo.CollectionVo(g.pkFleaGoodsId,u.pkFleaUserId,g.goodsName,g.goodsDescription,g.goodsPrice,g.goodsImgUrl,g.goodsMark,u.username,u.phoneNumber,c.createTime)" +
            "from FleaCollection c " +
            "left join c.fleaGoods g " +
            "left join c.fleaUser u where u.pkFleaUserId =:#{#judgeCollectionDto.userId} and g.pkFleaGoodsId =:#{#judgeCollectionDto.goodsId} ")
    List<CollectionVo> judgeCollection(JudgeCollectionDto judgeCollectionDto);
}
