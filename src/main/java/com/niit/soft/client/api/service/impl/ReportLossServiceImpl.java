package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.model.ReportLoss;
import com.niit.soft.client.api.domain.model.SysCard;
import com.niit.soft.client.api.domain.model.UserAccount;
import com.niit.soft.client.api.repository.CardRepository;
import com.niit.soft.client.api.repository.ReportLossRepository;
import com.niit.soft.client.api.repository.UserAccountRepository;
import com.niit.soft.client.api.service.ReportLossService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @ClassName ReportServiceImpl
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/1
 **/
@Service
public class ReportLossServiceImpl implements ReportLossService {
    @Resource
    private ReportLossRepository reportLossRepository;
    @Resource
    private UserAccountRepository userAccountRepository;
    @Resource
    private CardRepository cardRepository;

    /**
     * 分页查询所有信息
     *
     * @param pageDto
     * @return
     */
    @Override
    public ResponseResult findAllByPage(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage() - 1,
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pkReportLossId");
        Page<ReportLoss> reportLosses = reportLossRepository.findAll(pageable);
        return ResponseResult.success(reportLosses.getContent());
    }

    @Override
    public ResponseResult updateLossStatus(Long pkReportLossId, Boolean lossStatus) {
        return ResponseResult.success(reportLossRepository.updateLossStatus(pkReportLossId, lossStatus));
    }

    @Override
    public ResponseResult insertReportLoss(ReportLoss reportLoss) {
        //通过卡号查找帐号
        UserAccount userAccount = userAccountRepository.findUserAccountByCardNumber(reportLoss.getLossJobNumber());
        //通过挂失的卡号和卡密去查找一卡通数据
        SysCard sysCard = cardRepository.findSysCardByCardNumberAndCardPassword(reportLoss.getLossJobNumber(), reportLoss.getPassword());
        //用户是否存在
        if (userAccount != null) {
            //判断帐号状态 若为禁用状态无法挂失申请
            if (!userAccount.getStatus()) {
                //一卡通未申请过挂失
                if (!sysCard.getStatus()) {
                    ReportLoss reportLoss1 = ReportLoss.builder()
                            .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                            .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                            .isDeleted(false)
                            .lossJobNumber(reportLoss.getLossJobNumber())
                            .lossName(userAccount.getUserName())
                            .lossPhone(userAccount.getPhoneNumber())
                            .lossStatus(false)
                            .password(reportLoss.getPassword())
                            .remark(reportLoss.getRemark())
                            .build();
                    //新增信息
                    reportLossRepository.save(reportLoss1);
                    //修改一卡通的状态为禁用
                    cardRepository.updateStatus(sysCard.getPkCardId(), true);
                    return ResponseResult.success("申请挂失成功");
                } else {
                    return ResponseResult.failure(ResultCode.CARD_REPORT);
                }
            } else {
                return ResponseResult.failure(ResultCode.USER_ACCOUNT_FORBIDDEN);
            }
        } else {
            return ResponseResult.failure(ResultCode.USER_ACCOUNT_PASSWORD_ERROR);
        }
    }

    @Override
    public ResponseResult cancelReportLoss(ReportLoss reportLoss) {
        //查询挂失数据
        ReportLoss reportLoss1 = reportLossRepository.findReportLoss(reportLoss.getLossJobNumber(), reportLoss.getPassword());
        //查询一卡通数据
        SysCard sysCard = cardRepository.findSysCardByCardNumberAndCardPassword(reportLoss.getLossJobNumber(), reportLoss.getPassword());
        //挂失数据存在
        if (reportLoss1 != null) {
            //修改一卡通状态
            cardRepository.updateStatus(sysCard.getPkCardId(), false);
            //逻辑删除挂失记录
            reportLossRepository.deletedReportLoss(reportLoss1.getPkReportLossId());
            return ResponseResult.success("取消挂失成功！");
        } else {
            return ResponseResult.failure(ResultCode.DATA_IS_WRONG);
        }

    }
}