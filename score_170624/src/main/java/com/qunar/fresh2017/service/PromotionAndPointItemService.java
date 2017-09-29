package com.qunar.fresh2017.service;

import com.qunar.fresh2017.model.PromotionAndPointItemModel;
import com.qunar.fresh2017.support.ReturnMessage;

import java.util.List;

/**
 * 晋级分数和积分项模块service接口
 * @author lihuiminli
 * @create 2017-06-21 下午3:01
 */
public interface PromotionAndPointItemService {
    ReturnMessage savePromotionAndPointItemModel(PromotionAndPointItemModel promotionAndPointItemModel);

    ReturnMessage deleteById(int id);

    ReturnMessage queryAllByType(int type);

    ReturnMessage updatePromotionAndPointItemModel(PromotionAndPointItemModel promotionAndPointItemModel);
}
