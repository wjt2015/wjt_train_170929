package com.qunar.frc.demo5.service;

import com.qunar.frc.demo5.model.CatModel;

/**
 * Created by rq on 2016/6/15.
 */
public interface CatService {
    CatModel selectById(Integer id);
}