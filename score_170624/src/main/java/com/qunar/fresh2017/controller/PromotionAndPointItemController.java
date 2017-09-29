package com.qunar.fresh2017.controller;

import com.qunar.fresh2017.model.PromotionAndPointItemModel;
import com.qunar.fresh2017.service.PromotionAndPointItemService;
import com.qunar.fresh2017.support.ReturnMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 晋级分数和积分项操作模块controller
 * @author lihuiminli
 * @create 2017-06-22 下午2:13
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class PromotionAndPointItemController {

    @Resource
    private PromotionAndPointItemService promotionAndPointItemService;

    /**
     * 在晋级分数和积分项表中插入晋级记录
     * @param promotionAndPointItemModel 晋级分数或积分项信息
     * @return
     */
    @RequestMapping(value = "/addPromotionOrPointItem")
    public ReturnMessage addPromotionOrPointItem(PromotionAndPointItemModel promotionAndPointItemModel){
        return promotionAndPointItemService.savePromotionAndPointItemModel(promotionAndPointItemModel);
    }

    /**
     * 根据id在晋级分数和积分项表中物理删除记录
     * @param id 数据id
     * @param type 标明删除的是晋级分数（1）或者积分项（0）
     * @return
     */
    @RequestMapping(value = "/deletePromotionOrPointItem")
    public ReturnMessage deletePromotionOrPointItem(int id, int type){
        return promotionAndPointItemService.deleteById(id);
    }

    /**
     * 根据id在晋级分数和积分项表中物理删除记录
     * @param promotionAndPointItemModel 更新后的晋级分数或积分项信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePromotionOrPointItem")
    public ReturnMessage updatePromotionOrPointItem(PromotionAndPointItemModel promotionAndPointItemModel){
        return promotionAndPointItemService.updatePromotionAndPointItemModel(promotionAndPointItemModel);
    }

    /**
     * 根据type查询晋级分数和积分项表中的晋级状态详情（1）或者积分项详情
     * @param type 标明查询的是晋级分数（1）或者积分项（0）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryPromotionOrPointItemByTemp")
    public ReturnMessage queryPromotionOrPointItemByTemp(Integer type){
        return promotionAndPointItemService.queryAllByType(type);
    }
}
