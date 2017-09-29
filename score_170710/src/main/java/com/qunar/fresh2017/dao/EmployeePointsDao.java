package com.qunar.fresh2017.dao;

import com.qunar.fresh2017.model.EmployeePointsModel;
import com.qunar.fresh2017.model.QueryPointDetailModel;
import com.qunar.fresh2017.model.QueryPointDetailResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lfz on 2017/6/21.
 */
@Repository
public interface EmployeePointsDao {
    int getCount();

    List<QueryPointDetailResult> queryEmployeePoints(QueryPointDetailModel model);

    int updateEmployeePoints();

    int addEmployeePoints();

    int deleteOneEmployeePoints(int id);

    int deleteMultiEmployeePoints(int[] ids);
}
