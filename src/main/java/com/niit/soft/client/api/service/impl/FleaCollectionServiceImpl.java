package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.CancelCollectionDto;
import com.niit.soft.client.api.domain.dto.CollectionDto;
import com.niit.soft.client.api.domain.dto.FleaUserIdDto;
import com.niit.soft.client.api.domain.dto.JudgeCollectionDto;
import com.niit.soft.client.api.domain.model.FleaCollection;
import com.niit.soft.client.api.domain.vo.CollectionVo;
import com.niit.soft.client.api.repository.FleaCollectionRepository;
import com.niit.soft.client.api.repository.FleaGoodsRepository;
import com.niit.soft.client.api.repository.FleaUserRepository;
import com.niit.soft.client.api.service.FleaCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaCollectionServiceImpl.java
 * @Description TODO
 * @createTime 2020年06月09日 14:06:00
 */
@Slf4j
@Service
public class FleaCollectionServiceImpl implements FleaCollectionService {
    @Resource
    private FleaCollectionRepository collectionRepository;
    @Resource
    private FleaGoodsRepository goodsRepository;
    @Resource
    private FleaUserRepository userRepository;

    @Override
    public ResponseResult addCollection(CollectionDto collectionDto) {
////        if (goodsRepository.findById(collectionDto.getGoodsId()).get() == null &&
////        userRepository.findById(collectionDto.getUserId()).get() == null){
////            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
////        }
//        System.out.println(goodsRepository.findById(collectionDto.getGoodsId()).get() == null);
        FleaCollection fleaCollection = FleaCollection.builder()
                .fleaGoods(goodsRepository.findById(collectionDto.getGoodsId()).get())
                .fleaUser(userRepository.findById(collectionDto.getUserId()).get())
                .createTime(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false)
                .build();
        collectionRepository.save(fleaCollection);
        return ResponseResult.success("添加成功！");
    }

    @Override
    public ResponseResult getCollection(FleaUserIdDto userIdDto) {
        return ResponseResult.success(collectionRepository.getCollection(userIdDto));
    }

    @Override
    public ResponseResult logicalDel(CancelCollectionDto collectionDto) {
        return ResponseResult.success(collectionRepository.logicalDel(collectionDto));
    }

    @Override
    public ResponseResult judgeCollection(JudgeCollectionDto judgeCollectionDto) {
        List<CollectionVo> collectionVoList = collectionRepository.judgeCollection(judgeCollectionDto);
        if (collectionVoList.size() != 0) {
            return ResponseResult.success("已收藏！");
        }
        return ResponseResult.success("未收藏！");
    }
}
