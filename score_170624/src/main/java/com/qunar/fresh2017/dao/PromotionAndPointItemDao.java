package com.qunar.fresh2017.dao;

import com.qunar.fresh2017.model.PromotionAndPointItemModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 晋级分数和积分项表 dao层操作接口
 */
@Repository
public interface PromotionAndPointItemDao {
    /**
     * 保存晋级分数和积分项表数据
     *
     * @param model
     */
    int savePromotionAndPointItemModel(PromotionAndPointItemModel model);

    /**
     * 删除晋级分数和积分项表数据
     *
     * @param id 操作条数
     */
    int deletePromotionAndPointItemModelById(int id);

    /**
     * 更新晋级分数和积分项表数据
     *
     * @param model
     */
    int updatePromotionAndPointItemModel(PromotionAndPointItemModel model);

    /**
     * 根据type获取晋级分数或者积分项表数据
     *
     * @param type
     */
    List<PromotionAndPointItemModel> selectAllByType(int type);
}