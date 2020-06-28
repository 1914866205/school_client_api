package com.niit.soft.client.api.service;

import com.niit.soft.client.api.domain.model.FleaType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaTypeServiceTest.java
 * @Description TODO
 * @createTime 2020年06月24日 10:37:00
 */
@SpringBootTest
class FleaTypeServiceTest {

    @Resource
    private FleaTypeService fleaTypeService;
    @Test
    void findTopType() {
        fleaTypeService.findTopType();
    }
}