package com.qunar.frc.demo5.service.impl;

import com.qunar.frc.demo5.dao.bar.CatDao;
import com.qunar.frc.demo5.model.CatModel;
import com.qunar.frc.demo5.service.CatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by rq on 2016/6/15.
 */
@Service
@Slf4j
public class CatServiceImpl implements CatService {
    @Resource
    private CatDao catDao;

    public CatModel selectById(Integer id) {
        CatModel catModel = catDao.selectById(id);
        log.info("通过id = {}, 查询cat结果:{}", id, catModel.toString());
        return catModel;
    }
}