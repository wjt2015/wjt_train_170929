package com.qunar.fresh2017.service.impl;

import com.qunar.fresh2017.dao.EmployeePointsDao;
import com.qunar.fresh2017.model.QueryPointDetailModel;
import com.qunar.fresh2017.model.QueryPointDetailResult;
import com.qunar.fresh2017.service.EmployeePointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lfz on 2017/6/23.
 */
@Service
public class EmployeePointsServiceImpl implements EmployeePointsService {
    @Autowired
    private EmployeePointsDao dao;

    @Override
    public void deleteOneEmployeePoints(int id) {
        dao.deleteOneEmployeePoints(id);
    }

    @Override
    public void deleteMultiEmployeePoints(int[] ids) {
        if (ids != null && ids.length > 0) {
            dao.deleteMultiEmployeePoints(ids);
        }
    }

    @Override
    public List<QueryPointDetailResult> queryEmployeePoints(QueryPointDetailModel model) {
        return dao.queryEmployeePoints(model);
    }
}
