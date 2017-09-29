package com.qunar.fresh2017.service;

import com.qunar.fresh2017.model.*;
import com.qunar.fresh2017.support.ReturnMessage;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by lj on 17-6-24.
 */
public interface SumPointsService {
    /**
     * 根据条件统计员工积分
     * @param sumPointsQueryConditionModel 查询员工积分条件
     * @return
     */
    ReturnMessage queryCountPointsByCondition(SumPointsQueryConditionModel sumPointsQueryConditionModel);

    /**
     * 根据条件查询个人积分明细
     * @param sumPointsQueryConditionModel 查询个人积分条件
     * @return
     */
    ReturnMessage queryEmployeePointsByCondition(SumPointsQueryConditionModel sumPointsQueryConditionModel);

    /**
     * 插入晋级记录
     * @param promotionItemModel 晋级记录
     * @return
     */
    ReturnMessage savePromotionItem(PromotionItemModel promotionItemModel);

    /**
     * 导出个人明细
     * @param sumPointsExportConditionModel 个人明细查询条件
     * @return
     */
    ReturnMessage exportEmployeePoints(SumPointsExportConditionModel sumPointsExportConditionModel, HttpServletResponse httpServletResponse);
    /**
     * 导出统计积分
     * @param httpServletResponse
     * @return
     */
    ReturnMessage exportSumPoints(SumPointsExportConditionModel sumPointsExportConditionModel, HttpServletResponse httpServletResponse);
}
