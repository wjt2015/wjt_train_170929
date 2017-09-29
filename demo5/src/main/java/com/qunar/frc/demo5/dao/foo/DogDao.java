package com.qunar.frc.demo5.dao.foo;

import com.qunar.frc.demo5.model.DogModel;

/**
 * Created by rq on 2016/6/15.
 */
public interface DogDao {
    DogModel selectById(Integer id);
}