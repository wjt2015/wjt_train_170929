package com.qunar.fresh2017.controller;

import com.qunar.fresh2017.model.EmployeePointsModel;
import com.qunar.fresh2017.model.QueryPageResult;
import com.qunar.fresh2017.model.QueryPointDetailModel;
import com.qunar.fresh2017.model.QueryPointDetailResult;
import com.qunar.fresh2017.service.PointsDetailService;
import com.qunar.fresh2017.support.ReturnMessage;
import com.qunar.qtown.model.RtxUser;
import com.qunar.qtown.model.condition.RtxUserCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by lfz on 2017/6/23.
 */
@RestController()
@Slf4j
public class PointsDetailController {

    @Autowired
    private PointsDetailService service;

    /**
     * 根据主键单条删除积分明细
     *
     * @param id 主键id
     * @return
     */
    @RequestMapping("/pointsDetail/delete/{id}")
    public ReturnMessage deleteOneDetail(@PathVariable int id) {
        service.deleteOneEmployeePoints(id);
        return ReturnMessage.getReturnMessage(0, "操作成功", null);
    }

    /**
     * 根据主键的数组批量删除积分明细
     *
     * @param ids 主键id的数组
     * @return
     */
    @RequestMapping(value = "/pointsDetail/multiDelete", method = RequestMethod.POST)
    public ReturnMessage deleteMultiDetail(int[] ids) {
        service.deleteMultiEmployeePoints(ids);
        return ReturnMessage.getReturnMessage(0, "操作成功", null);
    }

    /**
     * 根据查询条件查询积分明细
     *
     * @param model
     * @return
     */
    @RequestMapping("/pointsDetail/queryDetail")
    public ReturnMessage queryPointsDetail(@RequestBody QueryPointDetailModel model) {
        System.out.println(model);
        QueryPageResult results = service.queryEmployeePoints(model);
        if (results == null) {
            return ReturnMessage.getReturnMessage(0, "操作失败，参数异常", null);
        }
        return ReturnMessage.getReturnMessage(0, "操作成功", results);
    }

    /**
     * 查询现有积分明细中所有的积分项和晋级记录
     *
     * @return
     */
    @RequestMapping("/pointsDetail/queryPointItemOrPromotionItem")
    public ReturnMessage queryPointItemOrPromotionItem() {
        return ReturnMessage.getReturnMessage(0, "操作成功", service.queryPointItemOrPromotionItem());
    }

    /**
     * 根据rtxId从大讲堂dubbo接口获得人员信息
     *
     * @param rtxId
     * @return
     */
    @RequestMapping("/pointsDetail/getMessageFromDubbo")
    public ReturnMessage queryPointItemOrPromotionItem(String rtxId) {
        if (rtxId == null || rtxId.trim().equals("")) {
            return ReturnMessage.getReturnMessage(2, "参数错误", null);
        }
        RtxUserCondition condition = new RtxUserCondition();
        condition.setRtxId(rtxId);
        List<RtxUser> result = service.getMessageFromDubbo(condition);
        if (result == null) {
            return ReturnMessage.getReturnMessage(1, "dubbo访问出错", null);
        }
        return ReturnMessage.getReturnMessage(0, "操作成功", result);
    }

    /**
     * 增加单条积分明细
     *
     * @param model
     * @return
     */
    @RequestMapping("/pointsDetail/addPointDetail")
    public ReturnMessage addPointsDetail(@RequestBody EmployeePointsModel model) {
        service.addPointsDetail(model);
        return ReturnMessage.getReturnMessage(0, "操作成功", null);
    }

    /**
     * 单条修改积分明细
     *
     * @param model
     * @return
     */
    @RequestMapping("/pointsDetail/updatePointDetail")
    public ReturnMessage updatePointsDetail(@RequestBody EmployeePointsModel model) {
        service.updatePointsDetail(model);
        return ReturnMessage.getReturnMessage(0, "操作成功", null);
    }

    /**
     * 导出条件查询结果
     *
     * @param model
     * @return
     */
    @RequestMapping("/pointsDetail/downLoadDetail")
    public ReturnMessage downLoadDetail(@RequestBody QueryPointDetailModel model) {
        System.out.println(model);
        List<QueryPointDetailResult> list = service.downLoadQueryEmployeePoints(model);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet hssfSheet = wb.createSheet("积分明细表");
        HSSFRow row = hssfSheet.createRow(0);


//        OutputStream outputStream=httpServletResponse.getOutputStream();
//        httpServletResponse.reset();
//        httpServletResponse.setHeader("Content-disposition","attachment;filename=temp.xls");
//        httpServletResponse.setContentType("application/vnc.ms-excel");
//        wb.write(outputStream);
//        outputStream.flush();
//        outputStream.close();
//        return ReturnMessage.getReturnMessage(0,"导出统计积分成功","");


        HSSFCellStyle hssfCellStyle = wb.createCellStyle();
        hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setFontName("黑体");
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        hssfCellStyle.setFont(fontStyle);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(hssfCellStyle);

        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(1, 3000);

        cell = row.createCell(2);
        cell.setCellValue("qtalk");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(2, 5000);

        cell = row.createCell(3);
        cell.setCellValue("岗位");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(3, 3000);

        cell = row.createCell(4);
        cell.setCellValue("主管");
        cell.setCellStyle(hssfCellStyle);

        cell = row.createCell(5);
        cell.setCellValue("一级部门");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(5, 4000);

        cell = row.createCell(6);
        cell.setCellValue("二级部门");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(6, 4000);

        cell = row.createCell(7);
        cell.setCellValue("三级部门");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(7, 4000);

        cell = row.createCell(8);
        cell.setCellValue("四级部门");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(8, 4000);

        cell = row.createCell(9);
        cell.setCellValue("积分项/晋级记录");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(9, 5000);

        cell = row.createCell(10);
        cell.setCellValue("主要内容");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(10, 5000);

        cell = row.createCell(11);
        cell.setCellValue("项目批次");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(11, 5000);

        cell = row.createCell(12);
        cell.setCellValue("时长");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(12, 5000);

        cell = row.createCell(13);
        cell.setCellValue("地点");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(13, 5000);

        cell = row.createCell(14);
        cell.setCellValue("时间");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(14, 5000);

        cell = row.createCell(15);
        cell.setCellValue("人数");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(15, 5000);

        cell = row.createCell(16);
        cell.setCellValue("学员评分");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(16, 5000);

        cell = row.createCell(17);
        cell.setCellValue("积分");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(17, 5000);

        cell = row.createCell(18);
        cell.setCellValue("关键评价/备注说明");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(18, 5000);

        cell = row.createCell(19);
        cell.setCellValue("最近操作记录");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(19, 5000);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        cellStyle.setFont(font);

        for (int i = 0; i < list.size(); i++) {
            row = hssfSheet.createRow(i + 1);
            QueryPointDetailResult result = list.get(i);

            cell = row.createCell(0);
            cell.setCellValue(i + 1);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue(result.getCn());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(result.getRtxId());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(3);
            cell.setCellValue(result.getJobCode());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(4);
            cell.setCellValue(result.getLeader());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(5);
            cell.setCellValue(result.getDep1());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(6);
            cell.setCellValue(result.getDep2());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(7);
            cell.setCellValue(result.getDep3());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(8);
            cell.setCellValue(result.getDep4());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(9);
            cell.setCellValue(result.getPointItemOrPromotionItem());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(10);
            cell.setCellValue(result.getMainContent());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(11);
            cell.setCellValue(result.getProjectPeriod());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(12);
            cell.setCellValue(result.getDuration());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(13);
            cell.setCellValue(result.getLocation());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(14);
            cell.setCellValue(result.getDate());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(15);
            cell.setCellValue(result.getPersonNum());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(16);
            cell.setCellValue(result.getScore().toString());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(17);
            cell.setCellValue(result.getPoints().toString());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(18);
            cell.setCellValue(result.getRemark());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(19);
            cell.setCellValue(result.getOperator()+result.getUpdateTime());
            cell.setCellStyle(cellStyle);

        }
        try {
            FileOutputStream fout = new FileOutputStream("/Users/lfz/test.xls");
            wb.write(fout);
            fout.close();
            return ReturnMessage.getReturnMessage(0, "导出个人积分明细成功", "");

        } catch (Exception e) {
            log.error("导出个人积分明细时出错", e.getMessage());
            return ReturnMessage.getReturnMessage(-1, e.getMessage(), "");
        }
    }



}
