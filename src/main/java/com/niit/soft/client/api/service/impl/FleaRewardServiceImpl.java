package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.dto.FleaSearchDto;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.dto.FleaRewardDto;
import com.niit.soft.client.api.domain.dto.SingleFieldDto;
import com.niit.soft.client.api.domain.model.FleaReward;
import com.niit.soft.client.api.domain.vo.RewardVo;
import com.niit.soft.client.api.repository.FleaRewardRepository;
import com.niit.soft.client.api.repository.FleaUserRepository;
import com.niit.soft.client.api.service.FleaRewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaRewardServiceImpl.java
 * @Description TODO
 * @createTime 2020年06月09日 14:06:00
 */
@Slf4j
@Service
public class FleaRewardServiceImpl implements FleaRewardService {
    @Resource
    private FleaRewardRepository fleaRewardRepository;
    @Resource
    private FleaUserRepository fleaUserRepository;

    @Override
    public Page<FleaReward> findFleaRewardByContent(FleaSearchDto fleaSearchDto) {
        //创建分页构建器   按照时间降序排序
        Pageable pageable = PageRequest.of(fleaSearchDto.getCurrentPage(), fleaSearchDto.getPageSize(), Sort.Direction.DESC, "goodsCreateTime");
        //根据内容模糊搜索
        List<FleaReward> result = fleaRewardRepository.findFleaRewardsByDescriptionLikeOrTitleLike("%" + fleaSearchDto.getContent() + "%", "%" + fleaSearchDto.getContent() + "%");
        Page<FleaReward> fleaGoodsInfo = new PageImpl<FleaReward>(result, pageable, result.size());
        return fleaGoodsInfo;
    }

    @Override
    public Page<FleaReward> findAll(PageDto pageDto) {
        //创建分页构建器   按照时间降序排序
        Pageable pageable = PageRequest.of(pageDto.getCurrentPage(), pageDto.getPageSize(), Sort.Direction.DESC, "createTime");
        return fleaRewardRepository.findAll(pageable);
    }

    @Override
    public ResponseResult getRewardTopThree() {
        Pageable pageable = PageRequest.of(0, 3, Sort.Direction.DESC, "createTime");
        List<RewardVo> rewardVoList = fleaRewardRepository.getTopReward(pageable);
        if (rewardVoList.size() == 0) {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
        return ResponseResult.success(rewardVoList);
    }

    @Override
    public ResponseResult save(FleaRewardDto fleaRewardDto) {
        FleaReward fleaReward = FleaReward.builder()
                .description(fleaRewardDto.getDescription())
                .imageUrl(fleaRewardDto.getImageUrl())
                .title(fleaRewardDto.getTitle())
                .fleaUser(fleaUserRepository.getOne(fleaRewardDto.getFleaUserId()))
                .createTime(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false).build();
        fleaRewardRepository.save(fleaReward);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult update(FleaRewardDto fleaRewardDto) {
        FleaReward fleaReward = fleaRewardRepository.findFleaRewardByPkRewardIdEquals(fleaRewardDto.getPkRewardId());
        fleaReward.setDescription(fleaRewardDto.getDescription());
        fleaReward.setImageUrl(fleaRewardDto.getImageUrl());
        fleaReward.setTitle(fleaRewardDto.getTitle());
        return ResponseResult.success(fleaRewardRepository.save(fleaReward));
    }

    @Override
    public ResponseResult delete(Long rewardId) {
        //查到了，就删除，数据消失
        if (fleaRewardRepository.findById(rewardId).isPresent()) {
            fleaRewardRepository.deleteById(rewardId);
        }
        return ResponseResult.success();
    }

    @Override
    public ResponseResult findById(Long rewardId) {
        return ResponseResult.success(fleaRewardRepository.getRewardByPkRewardId(rewardId));
    }
}