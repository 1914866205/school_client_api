package com.niit.soft.client.api.errends.service.servicempl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.model.UserAccount;
import com.niit.soft.client.api.errends.util.PageUtil;
import com.niit.soft.client.api.errends.domain.dto.DeliveryOrderDto;
import com.niit.soft.client.api.errends.domain.dto.FinshOrderDto;
import com.niit.soft.client.api.errends.domain.model.CancleDeliveryOrder;
import com.niit.soft.client.api.errends.domain.model.Commodity;
import com.niit.soft.client.api.errends.domain.model.DeliveryOrder;
import com.niit.soft.client.api.errends.domain.model.Transaction;
import com.niit.soft.client.api.errends.domain.vo.DeliveryOderInformationVo;
import com.niit.soft.client.api.errends.mapper.CommodityMapper;
import com.niit.soft.client.api.errends.mapper.DeliveryOrderMapper;
import com.niit.soft.client.api.errends.mapper.TransactionMapper;
import com.niit.soft.client.api.errends.repository.CancleDeliveryOderRepository;
import com.niit.soft.client.api.errends.repository.CommondityRepository;
import com.niit.soft.client.api.errends.repository.DeliveryOderRepository;
import com.niit.soft.client.api.errends.service.DeliveryOrederService;
import com.niit.soft.client.api.mapper.UserAccountMapper;
import com.niit.soft.client.api.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
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
 * @ClassNameDeliveryOrederServiceImpl
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
@Service
@Slf4j
@Transactional(rollbackFor = RuntimeException.class)
public class DeliveryOrederServiceImpl extends ServiceImpl<DeliveryOrderMapper, DeliveryOrder> implements DeliveryOrederService {
    @Resource
    private CommodityMapper commodityMapper;
    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;
    @Resource
    private TransactionMapper transactionMapper;
    @Resource
    private CommondityRepository commondityRepository;
    @Resource
    private DeliveryOderRepository deliveryOderRepository;
    @Resource
    private CancleDeliveryOderRepository cancleDeliveryOderRepository;
    @Resource
    private UserAccountMapper userAccountMapper;
    @Override
    public ResponseResult insertOrder(DeliveryOrderDto deliveryOrderDto) {
        SnowFlake snowFlaker = new SnowFlake(1, 3);
        //雪花生成商品id
        Long commodityId = snowFlaker.nextId();
        //雪花生成订单id
        Long deliveryOrderId = snowFlaker.nextId();


        //插入商品数据
        Commodity commodity = Commodity.builder().id(String.valueOf(commodityId)).priceRang(deliveryOrderDto.getPriceRang())
                .type(deliveryOrderDto.getType()).gmtCreate(Timestamp.valueOf(LocalDateTime.now())).gmtModified(Timestamp.valueOf(LocalDateTime.now())).isDeleted(false).build();
        Commodity save = commondityRepository.save(commodity);

        if (save != null) {
            //插入订单数据
            log.info(String.valueOf(deliveryOrderDto));
            DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                    .id(String.valueOf(deliveryOrderId))
                    .deliveryTime(Timestamp.valueOf(LocalDateTime.now())).amount(deliveryOrderDto.getAmount())
                    .commodityId(String.valueOf(commodityId))
                    .dDimension(deliveryOrderDto.getDdimension())
                    .destination(deliveryOrderDto.getDestination())
                    .dLongitude(deliveryOrderDto.getDlongitude())
                    .founderId(deliveryOrderDto.getFounderId())
                    .founderName(deliveryOrderDto.getFounderName()).founderPhonenumber(deliveryOrderDto.getFounderPhonenumer())
                    .status(0)
                    .oderCreateTime(Timestamp.valueOf(LocalDateTime.now())).oDimension(deliveryOrderDto.getOdimension())
                    .oLongitude(deliveryOrderDto.getOlongitude())
                    .originAddress(deliveryOrderDto.getOriginAddress())
                    .receiverName(deliveryOrderDto.getReceiverName())
                    .receiverPhoneNumber(deliveryOrderDto.getReceiverPhoneNumber())
                    .remark(deliveryOrderDto.getRemark())
                    .gmtCreate(Timestamp.valueOf(LocalDateTime.now())).gmtModified(Timestamp.valueOf(LocalDateTime.now())).isDeleted(false)

                    .build();
            DeliveryOrder deliveryOrderSave = deliveryOderRepository.save(deliveryOrder);
            if (deliveryOrderSave != null) {
                return ResponseResult.success();
            } else {
                return ResponseResult.failure(ResultCode.DATABASE_ERROR);
            }
        }
        return ResponseResult.failure(ResultCode.DATABASE_ERROR);
    }

    @Override
    public ResponseResult cancleOrder(String id) {
        //根居id查询出来的交易表是否有数据
        Transaction transaction = transactionMapper.selectById(id);
        if (transaction == null) {
            //没有数据
            //查询出来交易表中的id
            QueryWrapper<DeliveryOrder> deliveryOrderQueryWrapper = new QueryWrapper<>();
            DeliveryOrder deliveryOrder = DeliveryOrder.builder().status(1).build();
            deliveryOrderQueryWrapper.eq("id", id);
            int update = deliveryOrderMapper.update(deliveryOrder, deliveryOrderQueryWrapper);
            if (update != 0) {
                SnowFlake snowFlake = new SnowFlake(1, 3);
                long cancleId = snowFlake.nextId();
                CancleDeliveryOrder cancleDeliveryOrder = CancleDeliveryOrder.builder().cancleTime(Timestamp.valueOf(LocalDateTime.now())).id(String.valueOf(cancleId)).oderId(id).isDeleted(true).gmtCreate(Timestamp.valueOf(LocalDateTime.now())).gmtModified(Timestamp.valueOf(LocalDateTime.now())).build();
                cancleDeliveryOderRepository.save(cancleDeliveryOrder);
                return ResponseResult.success();
            }
        }
        return ResponseResult.failure(ResultCode.ORDER_NOT_CANCLE);
    }

    @Override
    public Map<String, Object> selectFinshOrder(FinshOrderDto finshOrderDto) {
        IPage<DeliveryOrder> page = new Page<>(finshOrderDto.getNum(), finshOrderDto.getSize());
        IPage<DeliveryOrder> pages;
        if (finshOrderDto.getStatus() != null) {
            QueryWrapper<DeliveryOrder> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("founder_id", finshOrderDto.getFounderId()).eq("status", finshOrderDto.getStatus()).orderByDesc("oder_create_time").eq("is_deleted", false);
            pages = deliveryOrderMapper.selectPage(page, queryWrapper);
        } else {
            QueryWrapper<DeliveryOrder> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("founder_id", finshOrderDto.getFounderId())
                    .orderByDesc("oder_create_time").eq("is_deleted", false);
            pages = deliveryOrderMapper.selectPage(page, queryWrapper);
        }
        //获取总页数
        Long totalPage = pages.getPages();
        //获取数据
        List<DeliveryOrder> records = pages.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("totalPage", totalPage);
        map.put("records", records);
        return map;
    }

    @Override
    public ResponseResult deleteOrder(String id) {
        log.info(id);
        QueryWrapper<DeliveryOrder> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("id", id).and(i-> i.ne("status", 0).or().ne("status", 2).ne("status", 3));
        DeliveryOrder deliveryOrder = DeliveryOrder.builder().isDeleted(true).build();
        int update = deliveryOrderMapper.update(deliveryOrder, queryWrapper);
        if(update==1){
            return ResponseResult.success();
        }else {
            return  ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }

    }

    @Override
    public ResponseResult selectAllOrder(FinshOrderDto finshOrderDto) {
        List<DeliveryOderInformationVo> list = new ArrayList<>();
        //分页减一
        Pageable pageable = PageRequest.of(finshOrderDto.getNum(), finshOrderDto.getSize());
        QueryWrapper<DeliveryOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "amount", "commodity_id", "d_dimension", "d_longitude", "delivery_time", "destination",
                "founder_id", "founder_name","founder_phonenumber","o_dimension", "o_longitude", "oder_create_time", "origin_address", "receiver_name", "receiver_phone_number"
                , "remark").eq("status", 0);
        List<DeliveryOrder> deliveryOrders = deliveryOrderMapper.selectList(queryWrapper);
        for (DeliveryOrder deliveryOrder : deliveryOrders) {
            //查出发单人信息
            QueryWrapper<UserAccount> userAccountQueryWrapper = new QueryWrapper<>();
            userAccountQueryWrapper.select("user_name", "job_number", "phone_number").eq("job_number", deliveryOrder.getFounderId());
            UserAccount userAccount = userAccountMapper.selectOne(userAccountQueryWrapper);
            Commodity commodity = commodityMapper.selectById(deliveryOrder.getCommodityId());
            DeliveryOderInformationVo deliveryOderInformationVo = DeliveryOderInformationVo.builder()
                    .amount(deliveryOrder.getAmount())
                    .commodity(commodity).deliveryTime(deliveryOrder.getDeliveryTime())
                    .destination(deliveryOrder.getDestination())
                    .founderId(userAccount.getJobNumber())
                    .founderName(deliveryOrder.getFounderName())
                    .founderPhonenumber(deliveryOrder.getFounderPhonenumber())
                    .id(deliveryOrder.getId())
                    .oderCreateTime(deliveryOrder.getOderCreateTime())
                    .originAddress(deliveryOrder.getOriginAddress())
                    .receiverName(deliveryOrder.getReceiverName())
                    .receiverPhoneNumber(deliveryOrder.getReceiverPhoneNumber())
                    .build();
            list.add(deliveryOderInformationVo);
        }

        org.springframework.data.domain.Page<DeliveryOderInformationVo> deliveryOderInformationVos = PageUtil.listConvertToPage(list, pageable);
        int totalPages = deliveryOderInformationVos.getTotalPages();
        List<DeliveryOderInformationVo> content = deliveryOderInformationVos.getContent();
        Map<String,Object>map =new HashMap<>();
        map.put("order",content);
        map.put("totalPages",totalPages);

        return ResponseResult.success(map);
    }


}
