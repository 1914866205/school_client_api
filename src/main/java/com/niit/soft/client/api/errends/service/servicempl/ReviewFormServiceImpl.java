package com.niit.soft.client.api.errends.service.servicempl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.errends.domain.dto.FinshOrderDto;
import com.niit.soft.client.api.errends.domain.dto.ReviewFormDto;

import com.niit.soft.client.api.errends.domain.model.ReviewForm;
import com.niit.soft.client.api.errends.mapper.ReviewFormMapper;
import com.niit.soft.client.api.errends.repository.ReviewFormRepository;
import com.niit.soft.client.api.errends.service.ReviewFormService;
import com.niit.soft.client.api.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author wl
 * @ClassNameReviewFormServiceImpl
 * @Description TODO
 * @Date 2020/6/12
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ReviewFormServiceImpl implements ReviewFormService {
    @Resource
    private ReviewFormRepository reviewFormRepository;
@Resource
private ReviewFormMapper reviewFormMapper;
    @Override
    public ResponseResult insertReview(ReviewFormDto reviewFormDto) {
        SnowFlake snowFlake=new SnowFlake(1,3);
        long l = snowFlake.nextId();
        //新增申请记录
        ReviewForm reviewForm =ReviewForm.builder()
                .id(String.valueOf(l)).gmtCreate(Timestamp.valueOf(LocalDateTime.now())).gmtModified(Timestamp.valueOf(LocalDateTime.now())).idCardBack(reviewFormDto.getIdCardBack())
                .idCardFront(reviewFormDto.getIdCardFront()).requesterId(reviewFormDto.getRequesterId())
                .remark(reviewFormDto.getRemark()).requesterName(reviewFormDto.getRequesterName()).requesterPhonenumber(reviewFormDto.getRequesterPhonenumber()).status(0L).isDeleted(false)
                .build();
      reviewFormRepository.save(reviewForm);
        return ResponseResult.success();

    }

    @Override
    public ResponseResult selectErrends(FinshOrderDto finshOrderDto ) {
        QueryWrapper<ReviewForm>reviewFormQueryWrapper=new QueryWrapper<>();
        //比对人 判断是否为跑腿
        reviewFormQueryWrapper.select("id","id_card_front","id_card_back","requester_id","requester_name","remark","requester_phonenumber"
        ,"status","gmt_create"
        ).eq("requester_id",finshOrderDto.getFounderId()).eq("status",finshOrderDto.getStatus());
        ReviewForm reviewForm = reviewFormMapper.selectOne(reviewFormQueryWrapper);
        if (reviewForm==null){
            return ResponseResult.failure(ResultCode.ERRENDS_NOT_HAVA_ROOT);
        }
        return ResponseResult.success(reviewForm);
    }

    @Override
    public ResponseResult selcetStatus(FinshOrderDto finshOrderDto) {
        QueryWrapper<ReviewForm>reviewFormQueryWrapper=new QueryWrapper<>();
        //比对人 判断是否为跑腿
        reviewFormQueryWrapper.select("id","id_card_front","id_card_back","requester_id","requester_name","remark","requester_phonenumber"
                ,"status","gmt_create"
        ).eq("requester_id",finshOrderDto.getFounderId());
        ReviewForm reviewForm = reviewFormMapper.selectOne(reviewFormQueryWrapper);
        return ResponseResult.success(reviewForm);
    }

}
