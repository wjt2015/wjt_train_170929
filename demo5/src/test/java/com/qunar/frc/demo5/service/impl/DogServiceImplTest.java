package com.qunar.frc.demo5.service.impl;

import com.qunar.frc.demo5.service.DogService;
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
public class DogServiceImplTest {
    @Resource
    private DogService dogService;

    @Test
    public void selectById() throws Exception {
        dogService.selectById(1);
    }
}