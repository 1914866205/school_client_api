package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.FleaOrderDto;
import com.niit.soft.client.api.domain.model.FleaOrder;
import com.niit.soft.client.api.repository.FleaGoodsRepository;
import com.niit.soft.client.api.repository.FleaOrderRepository;
import com.niit.soft.client.api.repository.FleaUserRepository;
import com.niit.soft.client.api.service.FleaOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaOrderServiceImpl.java
 * @Description TODO
 * @createTime 2020年06月09日 14:06:00
 */
@Slf4j
@Service
public class FleaOrderServiceImpl implements FleaOrderService {
    @Resource
    private FleaOrderRepository fleaOrderRepository;
    @Resource
    private FleaGoodsRepository fleaGoodsRepository;
    @Resource
    private FleaUserRepository fleaUserRepository;

    @Override
    public ResponseResult orderIncreased(FleaOrderDto fleaOrderDto) {
        FleaOrder fleaOrder = FleaOrder.builder()
                .pkFleaOrderId(fleaOrderDto.getPkFleaOrderId())
                .fleaGoods(fleaGoodsRepository.getOne(fleaOrderDto.getFleaGoodsPkFleaGoodsId()))
                .fleaUserBuyer(fleaUserRepository.getOne(fleaOrderDto.getFleaUserBuyerPkFleaUserId()))
                .fleaUserSeller(fleaUserRepository.getOne(fleaOrderDto.getFleaUserSellerPkFleaUserId()))
                .createTime(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false)
                .build();
        fleaOrderRepository.save(fleaOrder);
        return ResponseResult.success("OK");
    }

    @Override
    public ResponseResult logicalDel(FleaOrderDto fleaOrderDto) {
        return ResponseResult.success(fleaOrderRepository.logicalDel(fleaOrderDto));
    }
}
