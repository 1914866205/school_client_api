package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.dto.TypeDto;
import com.niit.soft.client.api.domain.model.FleaType;
import com.niit.soft.client.api.repository.FleaTypeRepository;
import com.niit.soft.client.api.service.FleaTypeService;
import com.niit.soft.client.api.util.TreeBuilder;
import com.niit.soft.client.api.util.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapIterator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaTypeServiceImpl.java
 * @Description TODO
 * @createTime 2020年06月09日 14:06:00
 */
@Slf4j
@Service
public class FleaTypeServiceImpl implements FleaTypeService {
    @Resource
    private FleaTypeRepository fleaTypeRepository;

    @Override
    public Map<String, Object> findAllType() {
        Map<String, Object> map = new TreeMap<>();
        List<TreeNode> list = new ArrayList<>();
        //查找所有的type
        List<FleaType> types = fleaTypeRepository.findAll();
        for (FleaType fleaType : types) {
            //如果没有父节点
            if (fleaType.getParentId() == 0) {
                TreeNode treeNode = new TreeNode(fleaType.getPkFleaTypeId(), 0L, fleaType.getTypeName(), fleaType.getTypeCoverUrl(), fleaType.getTypeUrl(), new ArrayList<>());
                list.add(treeNode);
            } else {
                TreeNode treeNode = new TreeNode(fleaType.getPkFleaTypeId(), fleaType.getParentId(), fleaType.getTypeName(), fleaType.getTypeCoverUrl(), fleaType.getTypeUrl(), new ArrayList<>());
                list.add(treeNode);
            }
        }
        List<TreeNode> trees = TreeBuilder.buildTreeByLoop(list);
        map.put("types", trees);
        return map;
    }

    @Override
    public ResponseResult getGoodsByType(TypeDto typeDto) {
        Pageable pageable = PageRequest.of(typeDto.getCurrentPage(), typeDto.getPageSize(), Sort.Direction.DESC, "goodsCreateTime");
        if (fleaTypeRepository.getGoodsByTypeId(pageable, typeDto).size() == 0) {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
        return ResponseResult.success(fleaTypeRepository.getGoodsByTypeId(pageable, typeDto));
    }

    @Override
    public Map<String, Object> findTopType() {
        List<TreeNode> allType = new ArrayList<>();
        //查找所有的type
        List<FleaType> types = fleaTypeRepository.findAll();
        for (FleaType fleaType : types) {
            //如果没有父节点
            if (fleaType.getParentId() == 0) {
                TreeNode treeNode = new TreeNode(fleaType.getPkFleaTypeId(), 0L, fleaType.getTypeName(), fleaType.getTypeCoverUrl(), fleaType.getTypeUrl(), new ArrayList<>());
                allType.add(treeNode);
            } else {
                TreeNode treeNode = new TreeNode(fleaType.getPkFleaTypeId(), fleaType.getParentId(), fleaType.getTypeName(), fleaType.getTypeCoverUrl(), fleaType.getTypeUrl(), new ArrayList<>());
                allType.add(treeNode);
            }
        }
        List<TreeNode> allTypeTree = TreeBuilder.buildTreeByLoop(allType);

        List<TreeNode> listShow = new ArrayList<>();
        TreeNode treeNode = null;
        for (int i = 0; i < 4; i++) {
            treeNode= allTypeTree.get(i);
            listShow.add(treeNode.getSubTypes().get(0));
        }
        Map<String, Object> map = new TreeMap<>();
        map.put("type", listShow);
        System.out.println(map);
        return map;
    }
}