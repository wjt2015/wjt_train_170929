package com.qunar.fresh2017.controller;

import com.qunar.fresh2017.model.EmployeePointsModel;
import com.qunar.fresh2017.model.QueryPointDetailModel;
import com.qunar.fresh2017.model.QueryPointDetailResult;
import com.qunar.fresh2017.service.EmployeePointsService;
import com.qunar.fresh2017.support.ReturnMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lfz on 2017/6/23.
 */
@RestController()
@Slf4j
public class PointsDetailController {

    @Autowired
    private EmployeePointsService service;

    @RequestMapping("/pointsDetail/delete/{id}")
    public ReturnMessage deleteOneDetail(@PathVariable int id){
        service.deleteOneEmployeePoints(id);
        return ReturnMessage.getReturnMessage(0, "操作成功", null);
    }

    @RequestMapping(value = "/pointsDetail/multiDelete",method = RequestMethod.POST)
    public ReturnMessage deleteMultiDetail(int[] ids){
        service.deleteMultiEmployeePoints(ids);
        return ReturnMessage.getReturnMessage(0, "操作成功", null);
    }

    @RequestMapping("/pointsDetail/queryDetail")
    public ReturnMessage queryPointsDetail(@RequestBody QueryPointDetailModel model) {
        List<QueryPointDetailResult> results = service.queryEmployeePoints(model);

        return ReturnMessage.getReturnMessage(0, "操作成功", service.queryEmployeePoints(model));
    }
}
