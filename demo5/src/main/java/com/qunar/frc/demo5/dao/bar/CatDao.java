package com.qunar.frc.demo5.dao.bar;

import com.qunar.frc.demo5.model.CatModel;

/**
 * Created by rq on 2016/6/15.
 */
public interface CatDao {
    CatModel selectById(Integer id);
}