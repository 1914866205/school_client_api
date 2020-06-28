package com.niit.soft.client.api.service.impl.schoolmate;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.dto.schoolmate.DynamicCollectionDto;
import com.niit.soft.client.api.domain.dto.schoolmate.DynamicCollectionInDto;
import com.niit.soft.client.api.domain.model.schoolmate.Collections;
import com.niit.soft.client.api.mapper.schoolmate.CollectionsMapper;
import com.niit.soft.client.api.repository.schoolmate.CollectionsRepository;
import com.niit.soft.client.api.repository.schoolmate.DynamicPhotoRepository;
import com.niit.soft.client.api.service.schoolmate.CollectionsService;
import com.niit.soft.client.api.util.SnowFlake;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName CollectionsServiceImpl
 * @Description 收藏
 * @Date 2020/6/11  9:49
 * @Version 1.0
 **/
@Service
public class CollectionsServiceImpl implements CollectionsService {
    @Resource
    private CollectionsMapper collectionsMapper;

    @Resource
    private CollectionsRepository collectionsRepository;

    @Resource
    private DynamicPhotoRepository dynamicPhotoRepository;

    @Override
    public ResponseResult findCollections(String id) {
        collectionsMapper.findCollectionsByUserId(id);
        return null;
    }

    @Override
    public ResponseResult getCollectionsByUserId(PageDto pageDto) {
        List<DynamicCollectionDto> collectionDtoArrayList = new ArrayList<>();

        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pk_collection_id");
        Page<Collections> collectionsPage =
                collectionsRepository.getCollectionsByUserId((String) pageDto.getField(), pageable);
        collectionsPage.forEach(collections -> {
            DynamicCollectionDto dynamicCollectionDto = collectionsMapper.findCollectionsByDynamicId(collections.getDynamicId());
            dynamicCollectionDto.setPkCollectionId(collections.getPkCollectionId());
            //得到一个资讯的所有配图
            List<String> dynamicPhotoList = dynamicPhotoRepository.findDistinctByDynamicId(collections.getDynamicId());
            if (dynamicPhotoList != null) {
                dynamicCollectionDto.setPicture(dynamicPhotoList);
            }
            collectionDtoArrayList.add(dynamicCollectionDto);
        });
        return ResponseResult.success(collectionDtoArrayList);
    }

    @Override
    public ResponseResult insertCollections(DynamicCollectionInDto dynamicCollectionInDto) {
        Collections collections1 = collectionsRepository.findCollectionsByUserIdAndDynamicIdAndIsDeleted(dynamicCollectionInDto.getUserId(), dynamicCollectionInDto.getDynamicId(), false);
        System.out.println(collections1);
        //判断时候存在，不存在才才能添加
        if (collections1 == null) {
            Collections collections = Collections.builder()
                    .pkCollectionId(String.valueOf(new SnowFlake(1, 3).nextId()))
                    .dynamicId(dynamicCollectionInDto.getDynamicId())
                    .userId(dynamicCollectionInDto.getUserId())
                    .isDeleted(false)
                    .build();
            collectionsRepository.save(collections);
            return ResponseResult.success("添加成功");
        }
        return ResponseResult.success(ResultCode.DATA_ALREADY_EXISTED);
    }


    @Override
    public ResponseResult updateCollectionsIsDelete(String id) {
        Collections collections = collectionsRepository.findCollectionsByPkCollectionId(id);
        collections.setIsDeleted(true);
        collectionsRepository.saveAndFlush(collections);
        return ResponseResult.success("删除成功");
    }
}
