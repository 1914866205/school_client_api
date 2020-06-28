package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.vo.DormVo;
import com.niit.soft.client.api.mapper.TowerMapper;
import com.niit.soft.client.api.service.TowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName TowerServiceImpl
 * @Description TODO
 * @date 2020-06-10 8:30
 **/
@Service
public class TowerServiceImpl implements TowerService {

    @Resource
    private TowerMapper towerMapper;

    @Override
    public ResponseResult getAllDormByType() {
        List<DormVo> dormVos = null;
        try {
            dormVos = towerMapper.getAllDormByType();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseResult.success(dormVos);
    }
}
