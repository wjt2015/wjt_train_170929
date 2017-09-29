package com.qunar.frc.demo5.service.impl;

import com.qunar.frc.demo5.service.CatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by rq on 2016/6/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class CatServiceImplTest {
    @Resource
    private CatService catService;

    @Test
    public void selectById() throws Exception {
        catService.selectById(1);
    }
}