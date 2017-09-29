package com.qunar.fresh2017.support.check;

import com.qunar.fresh2017.model.EmployeePointsModel;
import com.qunar.fresh2017.model.PromotionItemModel;
import com.qunar.fresh2017.model.SumPointsExportConditionModel;
import com.qunar.fresh2017.model.SumPointsQueryConditionModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 统计积分模块输入信息验证
 * Created by lj on 17-6-24.
 */
@Slf4j
@Repository
public class SumPointsCheck {
    /**
     * 统计积分查询条件验证
     * @param sumPointsQueryConditionModel
     * @return
     */
    public String sumPointsByCondition(SumPointsQueryConditionModel sumPointsQueryConditionModel) {
        String message = commonCheck(sumPointsQueryConditionModel);
        return message;
    }

    /**
     * 个人积分明细查询条件验证
     * @param sumPointsQueryConditionModel
     * @return
     */
    public String employeePointsByCondition(SumPointsQueryConditionModel sumPointsQueryConditionModel) {
        String message = commonCheck(sumPointsQueryConditionModel);
        //查询个人的积分明细时,Qtalk必须不为空
        if (message == null && sumPointsQueryConditionModel.getRtxId() == null) {
            log.error("查询条件中Qtalk为空", sumPointsQueryConditionModel.toString());
            return "查询条件中Qtalk为空";
        }
        return message;
    }

    /**
     * 导出时个人积分明细查询条件验证
     * @param sumPointsExportConditionModel
     * @return
     */
    public String exportEmployeePoints(SumPointsExportConditionModel sumPointsExportConditionModel){
        String message = exportConditionCheck(sumPointsExportConditionModel);
        //查询个人的积分明细时,Qtalk必须不为空
        if (message == null && sumPointsExportConditionModel.getRtxId() == null) {
            log.error("查询条件中Qtalk为空", sumPointsExportConditionModel.toString());
            return "查询条件中Qtalk为空";
        }
        return message;
    }

    /**
     * 导出时统计积分查询条件验证
     * @param sumPointsExportConditionModel
     * @return
     */
    public String exportSumPoints(SumPointsExportConditionModel sumPointsExportConditionModel){
        String message = exportConditionCheck(sumPointsExportConditionModel);
        return message;
    }

    /**
     * 晋级信息验证
     * @param promotionItemModel
     * @return
     */
    public String promotionItem(PromotionItemModel promotionItemModel) {
        //晋级操作时,晋级状态必须不为空
        if (promotionItemModel.getPromotionItem() == null||promotionItemModel.getPromotionItem().length()==0) {
            log.error("晋级状态为null或为空", promotionItemModel.toString());
            return "晋级状态为null或为空";
        }
        //晋级操作时,时间默认为今天
        if (promotionItemModel.getDate() == null) {
            promotionItemModel.setDate(new Date());
        }
        //晋级操作备注为null时,设置为空
        if (promotionItemModel.getRemark() == null) {
            promotionItemModel.setRemark("");
        }
        //晋级操作时,分数必须不为null
        if (promotionItemModel.getPoints() == null) {
            log.error("晋级时分数为null", promotionItemModel.toString());
            return "晋级时分数为null";
        }
        //晋级操作时userId必须不为null
        if (promotionItemModel.getUserId() == null) {
            log.error("晋级时userId为null", promotionItemModel.toString());
            return "晋级时userId为null";
        }
        //晋级操作时,操作人必须不为null或空
        if(promotionItemModel.getOperator()==null||promotionItemModel.getOperator().length()==0){
            log.error("操作人operator为null或为空",promotionItemModel.toString());
            return "操作人operator为null或为空";
        }
        return null;
    }

    /**
     * 统计积分和个人积分明细查询条件共同验证部分
     * @param sumPointsQueryConditionModel
     * @return
     */
    public String commonCheck(SumPointsQueryConditionModel sumPointsQueryConditionModel) {
        //分页参数order必须不为空或null
        if (sumPointsQueryConditionModel.getOrder().length() <= 0 || sumPointsQueryConditionModel.getOrder() == null) {
            log.error("分页参数中order为空或为null", sumPointsQueryConditionModel.toString());
            return "分页参数中order为空或为null";
        }
        //分页参数中sort必须不为空或null
        if (sumPointsQueryConditionModel.getSort().length() <= 0 || sumPointsQueryConditionModel.getSort() == null) {
            log.error("分页参数中sort为空或为null", sumPointsQueryConditionModel.toString());
            return "分页参数中sort为空或为null";
        }
        //分页参数中pageNum必须大于0
        if (sumPointsQueryConditionModel.getPageNum() <= 0) {
            log.error("分页参数中pageNum小于等于0", sumPointsQueryConditionModel.toString());
            return "分页参数中pageNum小于等于0";
        }
        //分页参数中pageIndex必须大于0
        if (sumPointsQueryConditionModel.getPageIndex() <= 0) {
            log.error("分页参数pageIndex小于等于0", sumPointsQueryConditionModel.toString());
            return "分页参数pageIndex小于等于0";
        }
        //查询条件中分数范围下限必须小于等于上限
        if (sumPointsQueryConditionModel.getPointsFloor() != null && sumPointsQueryConditionModel.getPointsUpper() != null && sumPointsQueryConditionModel.getPointsFloor().compareTo(sumPointsQueryConditionModel.getPointsUpper()) == 1) {
            log.error("分数范围下限大于上限", sumPointsQueryConditionModel.toString());
            return "分数范围下限大于上限";
        }
        //查询条件中日期范围开始时间必须小于等于结束时间
        if (sumPointsQueryConditionModel.getDateEnd() != null && sumPointsQueryConditionModel.getDateStart() != null && sumPointsQueryConditionModel.getDateEnd().compareTo(sumPointsQueryConditionModel.getDateStart()) == 0) {
            log.error("日期范围开始时间大于结束时间", sumPointsQueryConditionModel.toString());
            return "日期范围开始时间大于结束时间";
        }
        return null;
    }

    /**
     * 导出时共同验证部分
     * @param sumPointsExportConditionModel
     * @return
     */
    public String exportConditionCheck(SumPointsExportConditionModel sumPointsExportConditionModel){
        //查询条件中分数范围下限必须小于等于上限
        if (sumPointsExportConditionModel.getPointsFloor() != null && sumPointsExportConditionModel.getPointsUpper() != null && sumPointsExportConditionModel.getPointsFloor().compareTo(sumPointsExportConditionModel.getPointsUpper()) == 1) {
            log.error("分数范围下限大于上限", sumPointsExportConditionModel.toString());
            return "分数范围下限大于上限";
        }
        //查询条件中日期范围开始时间必须小于等于结束时间
        if (sumPointsExportConditionModel.getDateEnd() != null && sumPointsExportConditionModel.getDateStart() != null && sumPointsExportConditionModel.getDateEnd().compareTo(sumPointsExportConditionModel.getDateStart()) == 0) {
            log.error("日期范围开始时间大于结束时间", sumPointsExportConditionModel.toString());
            return "日期范围开始时间大于结束时间";
        }
        return null;
    }
}
