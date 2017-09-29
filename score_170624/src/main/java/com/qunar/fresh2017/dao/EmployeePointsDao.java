package com.qunar.fresh2017.dao;

import com.qunar.fresh2017.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lfz on 2017/6/21.
 */
@Repository
public interface EmployeePointsDao {
    int getQueryDetailCount(QueryPointDetailModel model);

    List<QueryPointDetailResult> queryEmployeePoints(QueryPointDetailModel model);

    int updateEmployeePoints(EmployeePointsModel model);

    void addEmployeePoints(EmployeePointsModel model);

    List<QueryPointDetailResult> downLoadQueryEmployeePoints(QueryPointDetailModel model);

    List<String> queryPointItemOrPromotionItem();

    void deleteOneEmployeePoints(int id);

    void deleteMultiEmployeePoints(int[] ids);

    /**
     * 根据查询条件统计积分
     * @param sumPointsQueryConditionModel
     * @return
     */
    List<SumPointsResultModel> selectSumPointsByCondition(SumPointsQueryConditionModel sumPointsQueryConditionModel);

    /**
     * 根据查询条件查询个人积分明细(分页)
     * @param sumPointsQueryConditionModel
     * @return
     */
    List<EmployeePointsModel> selectEmployeePointsByCondition(SumPointsQueryConditionModel sumPointsQueryConditionModel);

    /**
     * 根据查询条件查询个人积分明细(不分页)
     * @param sumPointsForExportCondition
     * @return
     */
    List<EmployeePointsModel> selectEmployeePointsForExport(SumPointsExportConditionModel sumPointsForExportCondition);
    /**
     * 根据查询条件查询统计积分(不分页)
     * @param sumPointsForExportCondition
     * @return
     */
    List<SumPointsResultModel> selectSumPointsForExport(SumPointsExportConditionModel sumPointsForExportCondition);
    /**
     * 根据查询条件查询统计积分的数量
     * @param sumPointsQueryConditionModel
     * @return
     */
    int selectSumPointsNum(SumPointsQueryConditionModel sumPointsQueryConditionModel);
    /**
     * 根据查询条件查询个人积分明细的数量
     * @param sumPointsQueryConditionModel
     * @return
     */
    int selectEmployeePointsNum(SumPointsQueryConditionModel sumPointsQueryConditionModel);
    /**
     * 保存晋级记录
     * @param promotionItemModel
     * @return
     */
    int savePromotionItem(PromotionItemModel promotionItemModel);
}
