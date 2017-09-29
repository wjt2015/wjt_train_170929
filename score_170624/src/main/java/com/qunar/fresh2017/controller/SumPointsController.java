package com.qunar.fresh2017.controller;

import com.qunar.fresh2017.model.EmployeePointsModel;
import com.qunar.fresh2017.model.PromotionItemModel;
import com.qunar.fresh2017.model.SumPointsExportConditionModel;
import com.qunar.fresh2017.model.SumPointsQueryConditionModel;
import com.qunar.fresh2017.service.SumPointsService;
import com.qunar.fresh2017.support.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 统计积分功能模块Controller
 * Created by lj on 17-6-22.
 */
@Controller
@Repository
@RequestMapping("/admin/sumPoints")
public class SumPointsController {
    @Autowired
    private SumPointsService sumPointsService;

    /**
     * 根据查询条件返回统计后的个人积分
     * @param sumPointsQueryConditionModel 分页参数和查询条件
     * @return
     */
    @ResponseBody
    @RequestMapping("/sumPointsByCondition")
    public ReturnMessage queryCountPoints(SumPointsQueryConditionModel sumPointsQueryConditionModel){
        return sumPointsService.queryCountPointsByCondition(sumPointsQueryConditionModel);
    }

    /**
     * 根据查询条件返回个人积分明细
     * @param sumPointsQueryConditionModel 查询条件
     * @return
     */
    @ResponseBody
    @RequestMapping("/employeePointsByCondition")
    public ReturnMessage queryEmployeePoints( SumPointsQueryConditionModel sumPointsQueryConditionModel){
        return sumPointsService.queryEmployeePointsByCondition(sumPointsQueryConditionModel);
    }

    /**
     * 在员工积分表中插入晋级记录
     * @param promotionItemModel 个人晋级记录信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertPromotionItem")
    public ReturnMessage savePromotionItem(PromotionItemModel promotionItemModel){
        return sumPointsService.savePromotionItem(promotionItemModel);
    }

    /**
     * 导出个人积分明细
     * @param sumPointsExportConditionModel 导出个人积分明细查询条件
     * @return
     */
    @ResponseBody
    @RequestMapping("/exportEmployeePoints")
    public ReturnMessage exportEmpoyeePoints(SumPointsExportConditionModel sumPointsExportConditionModel, HttpServletResponse httpServletResponse){
        return sumPointsService.exportEmployeePoints(sumPointsExportConditionModel,httpServletResponse);
    }

    /**
     * 导出统计积分
     * @param httpServletResponse
     * @return
     */
    @ResponseBody
    @RequestMapping("/exportSumPoints")
    public ReturnMessage exportSumPoints(SumPointsExportConditionModel sumPointsExportConditionModel, HttpServletResponse httpServletResponse){
        return sumPointsService.exportSumPoints(sumPointsExportConditionModel, httpServletResponse);
    }
}
