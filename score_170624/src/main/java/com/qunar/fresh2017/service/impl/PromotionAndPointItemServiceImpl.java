package com.qunar.fresh2017.service.impl;

import com.qunar.fresh2017.dao.PromotionAndPointItemDao;
import com.qunar.fresh2017.model.PromotionAndPointItemModel;
import com.qunar.fresh2017.service.PromotionAndPointItemService;
import com.qunar.fresh2017.support.ReturnMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * 晋级分数和积分项模块service接口实现
 *
 * @author lihuiminli
 * @create 2017-06-21 下午3:02
 */
@Slf4j
@Service
public class PromotionAndPointItemServiceImpl implements PromotionAndPointItemService {

    @Resource
    private PromotionAndPointItemDao promotionAndPointItemDao;

    @Override
    public ReturnMessage savePromotionAndPointItemModel(PromotionAndPointItemModel promotionAndPointItemModel) {
        int result = 0;
        try {
            result = promotionAndPointItemDao.savePromotionAndPointItemModel(promotionAndPointItemModel);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            if (errorMessage.indexOf("Duplicate") > 0) {
                return ReturnMessage.getReturnMessage(-1, "相同的名称已经存在！", null);
            }
            log.error("数据库操作失败！", e);
        }
        if (result > 0) {
            return ReturnMessage.getReturnMessage(0, "保存成功", null);
        } else {
            return ReturnMessage.getReturnMessage(-1, "保存失败", null);
        }

    }

    @Override
    public ReturnMessage deleteById(int id) {
        int result = 0;
        try {
            result = promotionAndPointItemDao.deletePromotionAndPointItemModelById(id);
        } catch (Exception e) {
            log.error("数据库操作失败！", e);
        }
        if (result > 0) {
            return ReturnMessage.getReturnMessage(0, "删除成功", null);
        } else {
            return ReturnMessage.getReturnMessage(-1, "删除失败", null);
        }
    }

    @Override
    public ReturnMessage updatePromotionAndPointItemModel(PromotionAndPointItemModel promotionAndPointItemModel) {
        int result = 0;
        try {
            result = promotionAndPointItemDao.updatePromotionAndPointItemModel(promotionAndPointItemModel);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            if (errorMessage.indexOf("Duplicate") > 0) {
                return ReturnMessage.getReturnMessage(-1, "相同的名称已经存在！", null);
            }
            log.error("数据库操作失败！", e);
        }
        if (result > 0) {
            return ReturnMessage.getReturnMessage(0, "更新成功", null);
        } else {
            return ReturnMessage.getReturnMessage(-1, "更新失败", null);
        }
    }

    @Override
    public ReturnMessage queryAllByType(int type) {
        List<PromotionAndPointItemModel> promotionAndPointItemModels;
        try {
            promotionAndPointItemModels = promotionAndPointItemDao.selectAllByType(type);
        } catch (Exception e) {
            log.error("数据库操作失败！", e);
            return ReturnMessage.getReturnMessage(-1, "查询失败", null);
        }
        return ReturnMessage.getReturnMessage(0, "查询成功", promotionAndPointItemModels);
    }
}
