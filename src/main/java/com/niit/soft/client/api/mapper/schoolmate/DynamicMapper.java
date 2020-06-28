package com.niit.soft.client.api.mapper.schoolmate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niit.soft.client.api.domain.model.schoolmate.Dynamic;
import com.niit.soft.client.api.domain.vo.schoolmate.DynamicVo;

/**
 * @ClassName DynamicMapper
 * @Description 好友圈动态资讯
 * @Author xiaobinggan
 * @Date 2020/6/8 4:43 下午
 * @Version 1.0
 **/
public interface DynamicMapper extends BaseMapper<Dynamic> {
    /**
     * 根据id查找动态资讯
     *
     * @param id
     * @return
     */
    DynamicVo findDynamicVoById(String id);

}
