package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.*;
import com.niit.soft.client.api.domain.model.FleaGoods;
import org.springframework.data.domain.Page;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaGoodsService.java
 * @Description TODO
 * @createTime 2020年06月09日 13:58:00
 */
public interface FleaGoodsService {
    /**
     * 模糊查询所有商品
     *
     * @param fleaSearchDto FleaSearchDto
     * @return Page<FleaGoods>
     */
    Page<FleaGoods> findFleaGoodsByContent(FleaSearchDto fleaSearchDto);

    /**
     * 根据时间分页查询商品
     *
     * @param pageDto PageDto
     * @return ResponseResult
     */
    ResponseResult getGoodsByTime(PageDto pageDto);

    /**
     * 根据商品id查询指定商品信息；
     *
     * @param goodIdDto GoodIdDto
     * @return ResponseResult
     */
    ResponseResult findGoodById(GoodIdDto goodIdDto);

    /**
     * 修改商品
     *
     * @param fleaGoodsDto FleaGoodsDto
     * @return ResponseResult
     */
    ResponseResult updateGood(FleaGoodsDto fleaGoodsDto);

    /**
     * 下架商品
     *
     * @param soldOutGoodDto SoldOutGoodDto
     * @return ResponseResult
     */
    ResponseResult soldOutGood(SoldOutGoodDto soldOutGoodDto);

    /**
     * 添加商品
     *
     * @param saveGoodDto SaveGoodDto
     * @return ResponseResult
     */
    ResponseResult saveGoods(SaveGoodDto saveGoodDto);

    /**
     * 查询top前五的标签
     *
     * @return ResponseResult
     */
    ResponseResult findTopFiveMark();
}
