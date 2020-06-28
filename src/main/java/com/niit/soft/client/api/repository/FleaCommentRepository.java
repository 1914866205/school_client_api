
package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.dto.FleaRewardDto;
import com.niit.soft.client.api.domain.model.FleaCollection;
import com.niit.soft.client.api.domain.model.FleaComment;
import com.niit.soft.client.api.domain.vo.FleaCommentVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName a.java
 * @Description TODO
 * @createTime 2020年06月09日 11:26:00
 */
public interface FleaCommentRepository extends JpaRepository<FleaComment, Long> {

    /**
     * 查询所有
     *
     * @param fleaRewardDto FleaRewardDto
     * @return List<FleaComment>
     */
    @Query(value = "select new com.niit.soft.client.api.domain.vo.FleaCommentVo(c.pkFleaCommentId,r.nickname,b.nickname,c.comment,f.title,c.createTime,b.avatar)" +
            "from FleaComment c " +
            "left join c.reviewer r " +
            "left join c.commentBy b " +
            "left join c.fleaReward f where f.pkRewardId =:#{#fleaRewardDto.pkRewardId}")
    List<FleaCommentVo> selectByRewardId(@Param("fleaRewardDto") FleaRewardDto fleaRewardDto);
}
