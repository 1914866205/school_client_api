package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.model.SysCard;

import java.util.List;

/**
 * @ClassName CardService
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/26
 **/
public interface CardService {
    /**
     * 分页查询一卡通数据
     *
     * @param pageDto
     * @return
     */
    ResponseResult findAllByPage(PageDto pageDto);

    /**
     * 批量增加一卡通信息
     *
     * @param sysCards
     * @return
     */
    ResponseResult insertAll(List<SysCard> sysCards);

    /**
     * 查询一卡通余额
     *
     * @param jobNumber
     * @return
     */
    ResponseResult selectCardBalance(String jobNumber);

    /**
     * 校园卡充值
     *
     * @param cardNumber
     * @param money
     * @return
     */
    ResponseResult insertCardBalance(String cardNumber, Double money);

    /**
     * 电费充值
     *
     * @param id
     * @param money
     * @return
     */

    ResponseResult insertelectricityBalance(Long id, Double money);

    /**
     * 网费充值
     *
     * @param jobNumber
     * @param money
     * @return
     */

    ResponseResult insertBalance(String jobNumber, Double money);

    /**
     * 校园卡激活
     *
     * @param pkCardId
     * @param Status
     * @return
     */
    ResponseResult updateStatus(Long pkCardId, Boolean Status);


}