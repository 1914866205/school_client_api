package com.niit.soft.client.api.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.model.SysCard;
import com.niit.soft.client.api.domain.model.SysOrder;
import com.niit.soft.client.api.repository.CardRepository;
import com.niit.soft.client.api.repository.OrderRepository;
import com.niit.soft.client.api.repository.RoomRepository;
import com.niit.soft.client.api.service.CardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CardServiceImpl
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/26
 **/
@Service
public class CardServiceImpl implements CardService {
    @Resource
    private CardRepository cardRepository;
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private RoomRepository roomRepository;

    @Override
    public ResponseResult findAllByPage(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pkCardId");
        Page<SysCard> sysCards = cardRepository.findAll(pageable);
        return ResponseResult.success(sysCards.getContent());
    }

    @Override
    public ResponseResult insertAll(List<SysCard> sysCards) {
        List<SysCard> sysCardList = new ArrayList<>();
        return ResponseResult.success(sysCardList);
    }

    @Override
    public ResponseResult selectCardBalance(String jobNumber) {
        Double cardBalance = cardRepository.findCardBalanceByJobNumber(jobNumber);
        return ResponseResult.success(cardBalance);
    }

    @Override
    public ResponseResult insertCardBalance(String cardNumber, Double money) {
        int a = cardRepository.insertCardBalance(cardNumber, money);
        SysOrder sysOrder =
                SysOrder.builder().description("校园卡充值").jobNumber(cardNumber).orderMoney(money).orderNumber("10" + RandomUtil.randomInt(80000, 1000000)).isDeleted(false).gmtCreate(Timestamp.valueOf(LocalDateTime.now())).orderType("校园卡充值").payMethod("支付宝转账").status(true).build();
        orderRepository.save(sysOrder);
        return ResponseResult.success(a);
    }

    @Override
    public ResponseResult insertelectricityBalance(Long id, Double money) {
        String jobNumber = roomRepository.findRoomLearderNumberById(id);
        int a = cardRepository.insertelectricityBalance(id, money);
        SysOrder sysOrder =
                SysOrder.builder().description("电费充值").jobNumber(jobNumber).orderMoney(money).orderNumber("10" + RandomUtil.randomInt(80000, 1000000)).isDeleted(false).gmtCreate(Timestamp.valueOf(LocalDateTime.now())).orderType("充值").payMethod("持卡人消费").status(true).build();
        orderRepository.save(sysOrder);
        return ResponseResult.success(a);
    }

    @Override
    public ResponseResult insertBalance(String jobNumber, Double money) {
        int a = cardRepository.insertBalance(jobNumber, money);
        SysOrder sysOrder =
                SysOrder.builder().description("网费充值").jobNumber(jobNumber).orderMoney(money).orderNumber("10" + RandomUtil.randomInt(80000, 1000000)).isDeleted(false).gmtCreate(Timestamp.valueOf(LocalDateTime.now())).orderType("充值").payMethod("持卡人消费").status(true).build();
        orderRepository.save(sysOrder);
        return ResponseResult.success(a);
    }

    @Override
    public ResponseResult updateStatus(Long pkCardId, Boolean Status) {
        return ResponseResult.success(cardRepository.updateStatus(pkCardId, Status));

    }
}