
package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.dto.SingleFieldDto;
import com.niit.soft.client.api.domain.model.FleaReward;
import com.niit.soft.client.api.domain.vo.RewardVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName a.java
 * @Description TODO
 * @createTime 2020年06月09日 11:26:00
 */
public interface FleaRewardRepository extends JpaRepository<FleaReward, Long> {
    /**
     * 根据description和title两个字段模糊查询
     *
     * @param description String
     * @param title       String
     * @return List<FleaReward>
     */
    List<FleaReward> findFleaRewardsByDescriptionLikeOrTitleLike(String description, String title);

    /**
     * 根据创建时间查出前两条数据
     *
     * @param pageable Pageable
     * @return List<RewardVo>
     */
    @Query(value = "select new com.niit.soft.client.api.domain.vo.RewardVo(f.title,f.description,f.imageUrl,f.createTime,u.username,u.phoneNumber) " +
            "from FleaReward f " +
            "left join f.fleaUser u " +
            "where f.isDeleted = false ")
    List<RewardVo> getTopReward(Pageable pageable);

    /**
     * 根据id查询
     *
     * @param pkRewardId Long
     * @return FleaReward
     */
    FleaReward findFleaRewardByPkRewardIdEquals(Long pkRewardId);

    /**
     * 根据id查询，VO显示
     *
     * @param rewardId Long
     * @return List<RewardVo>
     */
    @Query(value = "select new com.niit.soft.client.api.domain.vo.RewardVo(f.title,f.description,f.imageUrl,f.createTime,u.username,u.phoneNumber) " +
            "from FleaReward f " +
            "left join f.fleaUser u " +
            "where f.pkRewardId = ?1 ")
    List<RewardVo> getRewardByPkRewardId(Long rewardId);
}
