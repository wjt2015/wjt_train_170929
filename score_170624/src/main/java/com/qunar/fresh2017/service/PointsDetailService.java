package com.qunar.fresh2017.service;

import com.qunar.fresh2017.model.EmployeePointsModel;
import com.qunar.fresh2017.model.QueryPageResult;
import com.qunar.fresh2017.model.QueryPointDetailModel;
import com.qunar.fresh2017.model.QueryPointDetailResult;
import com.qunar.qtown.model.RtxUser;
import com.qunar.qtown.model.condition.RtxUserCondition;

import java.util.List;

/**
 * Created by lfz on 2017/6/23.
 */
public interface PointsDetailService {
    void deleteOneEmployeePoints(int id);

    void deleteMultiEmployeePoints(int[] ids);

    QueryPageResult queryEmployeePoints(QueryPointDetailModel model);

    int addEmployeePoints(EmployeePointsModel model);

    List<String> queryPointItemOrPromotionItem();

    List<RtxUser> getMessageFromDubbo(RtxUserCondition condition);

    void addPointsDetail(EmployeePointsModel model);

    void updatePointsDetail(EmployeePointsModel model);

    List<QueryPointDetailResult> downLoadQueryEmployeePoints(QueryPointDetailModel model);
}
