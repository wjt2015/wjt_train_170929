package com.qunar.fresh2017.service;

import com.qunar.fresh2017.model.QueryPointDetailModel;
import com.qunar.fresh2017.model.QueryPointDetailResult;

import java.util.List;

/**
 * Created by lfz on 2017/6/23.
 */
public interface EmployeePointsService {
    void deleteOneEmployeePoints(int id);

    void deleteMultiEmployeePoints(int[] ids);

    List<QueryPointDetailResult> queryEmployeePoints(QueryPointDetailModel model);
}
