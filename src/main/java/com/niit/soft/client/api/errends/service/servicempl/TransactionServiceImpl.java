package com.niit.soft.client.api.errends.service.servicempl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.model.UserAccount;
import com.niit.soft.client.api.errends.util.PageUtil;
import com.niit.soft.client.api.errends.domain.dto.FinshOrderDto;
import com.niit.soft.client.api.errends.domain.dto.TransactionDto;
import com.niit.soft.client.api.errends.domain.model.Commodity;
import com.niit.soft.client.api.errends.domain.model.DeliveryOrder;
import com.niit.soft.client.api.errends.domain.model.Transaction;
import com.niit.soft.client.api.errends.domain.vo.DeliveryOderInformationVo;
import com.niit.soft.client.api.errends.mapper.CommodityMapper;
import com.niit.soft.client.api.errends.mapper.DeliveryOrderMapper;
import com.niit.soft.client.api.errends.mapper.TransactionMapper;
import com.niit.soft.client.api.errends.repository.TransactionRepository;
import com.niit.soft.client.api.errends.service.TransactionService;
import com.niit.soft.client.api.mapper.UserAccountMapper;
import com.niit.soft.client.api.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wl
 * @ClassNameTransactionServiceImpl
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class TransactionServiceImpl implements TransactionService {
    @Resource
    private TransactionRepository transactionRepository;
    @Resource
    private TransactionMapper transactionMapper;
    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;
    @Resource
    private CommodityMapper commodityMapper;
    @Resource
    private UserAccountMapper userAccountMapper;

    @Override
    public ResponseResult insertTransaction(TransactionDto transactionDto) {
        SnowFlake snowFlake = new SnowFlake(1, 3);
        long transactionId = snowFlake.nextId();
        //创建订单表
        Transaction transaction = Transaction.builder().transactionCreate(Timestamp.valueOf(LocalDateTime.now()))
                .transactionEnd(Timestamp.valueOf(LocalDateTime.now())).errandsId(transactionDto.getErrandsId()).status(0).orderId(transactionDto.getOrderId())
                .id(String.valueOf(transactionId)).gmtCreate(Timestamp.valueOf(LocalDateTime.now())).gmtModified(Timestamp.valueOf(LocalDateTime.now())).isDeleted(false).build();
        Transaction save = transactionRepository.save(transaction);
        //更改订单状态为被抢单
        QueryWrapper<DeliveryOrder>queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("id",transactionDto.getOrderId());
        DeliveryOrder deliveryOrder = DeliveryOrder.builder().status(4).build();
        deliveryOrderMapper.update(deliveryOrder,queryWrapper);
        return ResponseResult.success();
    }


    @Override
    public ResponseResult finshOrder(String orderId) {
        /**
         * 更新订单交易表的状态
         */
        QueryWrapper<Transaction> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        Transaction transaction = Transaction.builder().status(3).build();
        transactionMapper.update(transaction, queryWrapper);
        //更改订单状态为完成 3
        QueryWrapper<DeliveryOrder> deliveryOrderQueryWrapper = new QueryWrapper<>();
        deliveryOrderQueryWrapper.eq("id", orderId);
        DeliveryOrder deliveryOrder = DeliveryOrder.builder().status(3).build();
        deliveryOrderMapper.update(deliveryOrder, deliveryOrderQueryWrapper);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult getGoods(TransactionDto transactionDto) {
        /**
         * 更新交易表状态 为1 取货并且送货
         */
        QueryWrapper<Transaction> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", transactionDto.getOrderId());
        Transaction transaction = Transaction.builder().status(1).build();
        transactionMapper.update(transaction, queryWrapper);
        //更改订单状态为正在运送
        QueryWrapper<DeliveryOrder> deliveryOrderQueryWrapper = new QueryWrapper<>();
        deliveryOrderQueryWrapper.eq("id", transactionDto.getOrderId());
        DeliveryOrder deliveryOrder = DeliveryOrder.builder().status(2).build();
        deliveryOrderMapper.update(deliveryOrder, deliveryOrderQueryWrapper);
        return ResponseResult.success();

    }

    @Override
    public ResponseResult selctTransactionOrder(FinshOrderDto finshOrderDto) {

        List<DeliveryOderInformationVo> list = new ArrayList<>();
        //分页减一
        Pageable pageable = PageRequest.of(finshOrderDto.getNum(), finshOrderDto.getSize());
        //查询所有订单
        QueryWrapper<Transaction> transactionQueryWrapper = new QueryWrapper<>();
        transactionQueryWrapper.select("errands_id", "order_id", "transaction_create", "transaction_end").eq("status", finshOrderDto.getStatus()).eq("errands_id", finshOrderDto.getFounderId()).orderByDesc("transaction_create");
        List<Transaction> transactions = transactionMapper.selectList(transactionQueryWrapper);


        for (Transaction transaction : transactions) {
            //查询出订单消息
            DeliveryOrder deliveryOrder = deliveryOrderMapper.selectById(transaction.getOrderId());
            log.info(transaction.getOrderId());
            //查出商品信息
            Commodity commodity = commodityMapper.selectById(deliveryOrder.getCommodityId());

            //查出送货人信息
            QueryWrapper<UserAccount> errendsAccountWrapper = new QueryWrapper<>();
            errendsAccountWrapper.select("nickname", "job_number", "phone_number").eq("job_number", finshOrderDto.getFounderId());
            UserAccount errendsAccount = userAccountMapper.selectOne(errendsAccountWrapper);
            //查出收货人
            QueryWrapper<UserAccount> userAccountQueryWrapper = new QueryWrapper<>();
            userAccountQueryWrapper.select("nickname", "job_number", "phone_number").eq("job_number", deliveryOrder.getFounderId());
            UserAccount userAccount = userAccountMapper.selectOne(userAccountQueryWrapper);
            DeliveryOderInformationVo deliveryOderInformationVo = DeliveryOderInformationVo.builder()
                    .amount(deliveryOrder.getAmount()).commodity(commodity)
                    .deliveryTime(deliveryOrder.getDeliveryTime())
                    .destination(deliveryOrder.getDestination())
                    .errendsPhoneNumber(errendsAccount.getPhoneNumber())
                    .finshTime(transaction.getTransactionEnd()).founderId(deliveryOrder.getFounderId()).founderName(userAccount.getNickname()).founderPhonenumber(userAccount.getPhoneNumber())
                    .id(deliveryOrder.getId()).name(errendsAccount.getNickname()).oderCreateTime(deliveryOrder.getOderCreateTime())
                    .originAddress(deliveryOrder.getOriginAddress()).receiverName(deliveryOrder.getReceiverName()).receiverPhoneNumber(deliveryOrder.getReceiverPhoneNumber())
                    .remark(deliveryOrder.getRemark())
                    .build();
            list.add(deliveryOderInformationVo); }
        org.springframework.data.domain.Page<DeliveryOderInformationVo> deliveryOderInformationVos = PageUtil.listConvertToPage(list, pageable);
        int totalPages = deliveryOderInformationVos.getTotalPages();
        List<DeliveryOderInformationVo> content = deliveryOderInformationVos.getContent();
        Map<String,Object>map =new HashMap<>();
        map.put("order",content);
        map.put("totalPages",totalPages);

        return ResponseResult.success(map);

    }




}