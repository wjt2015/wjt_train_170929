package com.qunar.frc.demo5.service;

import com.qunar.frc.demo5.model.DogModel;

/**
 * Created by rq on 2016/6/15.
 */
public interface DogService {
    DogModel selectById(Integer id);
}
