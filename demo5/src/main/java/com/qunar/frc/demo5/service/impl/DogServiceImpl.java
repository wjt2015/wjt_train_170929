package com.qunar.frc.demo5.service.impl;

import com.qunar.frc.demo5.dao.foo.DogDao;
import com.qunar.frc.demo5.model.DogModel;
import com.qunar.frc.demo5.service.DogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by rq on 2016/6/15.
 */
@Service
@Slf4j
public class DogServiceImpl implements DogService {
    @Resource
    private DogDao dogDao;

    public DogModel selectById(Integer id) {
        DogModel dogModel = dogDao.selectById(id);
        log.info("通过id = {},查询dog结果:{}", id, dogModel.toString());
        return dogModel;
    }
}
