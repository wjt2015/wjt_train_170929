package com.qunar.fresh2017.service.impl;

import com.google.common.collect.Maps;
import com.qunar.fresh2017.dao.EmployeePointsDao;
import com.qunar.fresh2017.model.*;
import com.qunar.fresh2017.service.SumPointsService;
import com.qunar.fresh2017.support.ReturnMessage;
import com.qunar.fresh2017.support.check.SumPointsCheck;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 统计积分模块Service
 * Created by lj on 17-6-22.
 */
@Slf4j
@Resource
@Service
public class SumPointsServiceImpl implements SumPointsService{
    @Autowired
    private EmployeePointsDao employeePointsDao;
    @Autowired
    private SumPointsCheck sumPointsCheck;

    /**
     * 根据查询条件统计个人积分
     * @param sumPointsQueryConditionModel 查询员工积分条件
     * @return
     */
    @Override
    public ReturnMessage queryCountPointsByCondition(SumPointsQueryConditionModel sumPointsQueryConditionModel){
        String message=sumPointsCheck.sumPointsByCondition(sumPointsQueryConditionModel);
        if(message!=null){
            return ReturnMessage.getReturnMessage(-1,message,null);
        }
        sumPointsQueryConditionModel.setPageIndex((sumPointsQueryConditionModel.getPageIndex()-1)*sumPointsQueryConditionModel.getPageNum());
        QueryPageResult queryPageResult=new QueryPageResult();
        queryPageResult.setTotalCount(employeePointsDao.selectSumPointsNum(sumPointsQueryConditionModel));
        queryPageResult.setContent(employeePointsDao.selectSumPointsByCondition(sumPointsQueryConditionModel));
        return ReturnMessage.getReturnMessage(0,"查询统计积分成功",queryPageResult);
    }

    /**
     * 根据查询条件返回个人积分明细
     * @param sumPointsQueryConditionModel 查询条件
     * @return
     */
    @Override
    public ReturnMessage queryEmployeePointsByCondition(SumPointsQueryConditionModel sumPointsQueryConditionModel){
        String message=sumPointsCheck.employeePointsByCondition(sumPointsQueryConditionModel);
        if(message!=null){
            return ReturnMessage.getReturnMessage(-1,message,null);
        }
        sumPointsQueryConditionModel.setPageIndex((sumPointsQueryConditionModel.getPageIndex()-1)*sumPointsQueryConditionModel.getPageNum());
        QueryPageResult queryPageResult=new QueryPageResult();
        queryPageResult.setTotalCount(employeePointsDao.selectEmployeePointsNum(sumPointsQueryConditionModel));
        queryPageResult.setContent(employeePointsDao.selectEmployeePointsByCondition(sumPointsQueryConditionModel));
        return ReturnMessage.getReturnMessage(0,"查询个人积分成功",queryPageResult);
    }

    /**
     * 晋级操作,只保留晋级信息,其他信息设为空
     * @param promotionItemModel 晋级记录
     * @return
     */
    @Override
    public ReturnMessage savePromotionItem(PromotionItemModel promotionItemModel){
        String message=sumPointsCheck.promotionItem(promotionItemModel);
        if(message!=null){
            return ReturnMessage.getReturnMessage(-1,message,null);
        }
        int result=employeePointsDao.savePromotionItem(promotionItemModel);
        if(result==0){
            return ReturnMessage.getReturnMessage(-1,"数据库操作出错",null);
        }
        return ReturnMessage.getReturnMessage(0,"晋级记录保存成功",null);
    }

    /**
     * 导出个人积分明细
     * @param sumPointsExportConditionModel 个人明细查询条件
     * @return
     */
    @Override
    public ReturnMessage exportEmployeePoints(SumPointsExportConditionModel sumPointsExportConditionModel, HttpServletResponse httpServletResponse){
        String message=sumPointsCheck.exportEmployeePoints(sumPointsExportConditionModel);
        if(message!=null){
            return ReturnMessage.getReturnMessage(-1,message,null);
        }
        List<EmployeePointsModel> employeePointsModelsList=employeePointsDao.selectEmployeePointsForExport(sumPointsExportConditionModel);
        if(employeePointsModelsList==null){
            return ReturnMessage.getReturnMessage(-1,"导出个人积分明细查询操作返回null","");
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet hssfSheet=wb.createSheet("个人积分明细表");
        HSSFFont  fontStyle=wb.createFont();
        HSSFRow row = hssfSheet.createRow((int) 0);
        HSSFCellStyle hssfCellStyle=wb.createCellStyle();
        hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        fontStyle.setFontName("黑体");
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        hssfCellStyle.setFont(fontStyle);

        HSSFCell cell=row.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(hssfCellStyle);
        cell=row.createCell(1);
        cell.setCellValue("积分项/晋级记录");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(1,3000);
        cell=row.createCell(2);
        cell.setCellValue("主要内容");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(2,5000);
        cell=row.createCell(3);
        cell.setCellValue("项目期次");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(3,3000);
        cell=row.createCell(4);
        cell.setCellValue("时长");
        cell.setCellStyle(hssfCellStyle);
        cell=row.createCell(5);
        cell.setCellValue("地点");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(5,4000);
        cell=row.createCell(6);
        cell.setCellValue("时间");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(6,3000);
        cell=row.createCell(7);
        cell.setCellValue("学员评分");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(7,3000);
        cell=row.createCell(8);
        cell.setCellValue("积分");
        cell.setCellStyle(hssfCellStyle);
        cell=row.createCell(9);
        cell.setCellValue("关键评价/备注说明");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(9,5000);

        HSSFCellStyle cellStyle=wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont  font=wb.createFont();
        font.setFontName("宋体");
        cellStyle.setFont(font);

        for(int i=0;i<employeePointsModelsList.size();i++){
            row=hssfSheet.createRow(i+1);
            EmployeePointsModel empl=employeePointsModelsList.get(i);

            cell=row.createCell(0);
            cell.setCellValue(i+1);
            cell.setCellStyle(cellStyle);
            cell=row.createCell(1);
            cell.setCellValue(empl.getPointItemOrPromotionItem());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(2);
            cell.setCellValue(empl.getMainContent());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(3);
            cell.setCellValue(empl.getProjectPeriod());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(4);
            cell.setCellValue(empl.getDuration());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(5);
            cell.setCellValue(empl.getLocation());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(6);
            cell.setCellValue(empl.getDate());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(7);
            cell.setCellValue(empl.getScore().toString());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(8);
            cell.setCellValue(empl.getPoints().toString());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(9);
            cell.setCellValue(empl.getRemark());
            cell.setCellStyle(cellStyle);
        }
        try
        {
//            FileOutputStream fout = new FileOutputStream("/home/lj/test/employee_points.xls");
//            wb.write(fout);
//            fout.close();
            OutputStream  outputStream=httpServletResponse.getOutputStream();
            httpServletResponse.reset();
            httpServletResponse.setHeader("Content-disposition","attachment;filename=temp.xls");
            httpServletResponse.setContentType("application/vnc.ms-excel");
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return ReturnMessage.getReturnMessage(0,"导出个人积分明细成功","");

        }
        catch (Exception e)
        {
            log.error("导出个人积分明细时出错",e.getMessage());
            return ReturnMessage.getReturnMessage(-1,e.getMessage(),"");
        }
    }

    /**
     * 导出统计积分
     * @param httpServletResponse
     * @return
     */
    public ReturnMessage exportSumPoints(SumPointsExportConditionModel sumPointsExportConditionModel, HttpServletResponse httpServletResponse){
        String message=sumPointsCheck.exportSumPoints(sumPointsExportConditionModel);
        if(message!=null){
            return ReturnMessage.getReturnMessage(-1,message,null);
        }
        List<SumPointsResultModel> sumPointsModelsList=employeePointsDao.selectSumPointsForExport(sumPointsExportConditionModel);
        if(sumPointsModelsList==null){
            return ReturnMessage.getReturnMessage(-1,"导出统计积分查询操作返回null","");
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet hssfSheet=wb.createSheet("统计积分表");
        HSSFFont  fontStyle=wb.createFont();
        HSSFRow row = hssfSheet.createRow((int) 0);
        HSSFCellStyle hssfCellStyle=wb.createCellStyle();
        hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        fontStyle.setFontName("黑体");
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        hssfCellStyle.setFont(fontStyle);

        HSSFCell cell=row.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(hssfCellStyle);
        cell=row.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(1,3000);
        cell=row.createCell(2);
        cell.setCellValue("Qtalk");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(2,3000);
        cell=row.createCell(3);
        cell.setCellValue("岗位编码");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(3,2000);
        cell=row.createCell(4);
        cell.setCellValue("主管");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(4,3000);
        cell=row.createCell(5);
        cell.setCellValue("地点");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(5,4000);
        cell=row.createCell(6);
        cell.setCellValue("用工类型");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(6,3000);
        cell=row.createCell(7);
        cell.setCellValue("一级部门");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(7,3000);
        cell=row.createCell(8);
        cell.setCellValue("二级部门");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(8,3000);
        cell=row.createCell(9);
        cell.setCellValue("三级部门");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(9,3000);
        cell=row.createCell(10);
        cell.setCellValue("四级部门");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(10,3000);
        cell=row.createCell(11);
        cell.setCellValue("五级部门");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(11,3000);
        cell=row.createCell(12);
        cell.setCellValue("当前积分");
        cell.setCellStyle(hssfCellStyle);
        hssfSheet.setColumnWidth(12,3000);

        HSSFCellStyle cellStyle=wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont  font=wb.createFont();
        font.setFontName("宋体");
        cellStyle.setFont(font);

        for(int i=0;i<sumPointsModelsList.size();i++){
            row=hssfSheet.createRow(i+1);
            SumPointsResultModel sumPonits=sumPointsModelsList.get(i);

            cell=row.createCell(0);
            cell.setCellValue(i+1);
            cell.setCellStyle(cellStyle);
            cell=row.createCell(1);
            cell.setCellValue(sumPonits.getCn());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(2);
            cell.setCellValue(sumPonits.getRtxId());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(3);
            cell.setCellValue(sumPonits.getJobCode());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(4);
            cell.setCellValue(sumPonits.getLeader());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(5);
            cell.setCellValue(sumPonits.getHireType());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(6);
            cell.setCellValue(sumPonits.getDep1());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(7);
            cell.setCellValue(sumPonits.getDep2());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(8);
            cell.setCellValue(sumPonits.getDep3());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(9);
            cell.setCellValue(sumPonits.getDep4());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(11);
            cell.setCellValue(sumPonits.getDep5());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(12);
            cell.setCellValue(sumPonits.getCountPoints().toString());
            cell.setCellStyle(cellStyle);
        }
        try
        {
//            FileOutputStream fout = new FileOutputStream("/home/lj/test/sum_points.xls");
//            wb.write(fout);
//            fout.close();
            OutputStream  outputStream=httpServletResponse.getOutputStream();
            httpServletResponse.reset();
            httpServletResponse.setHeader("Content-disposition","attachment;filename=temp.xls");
            httpServletResponse.setContentType("application/vnc.ms-excel");
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return ReturnMessage.getReturnMessage(0,"导出统计积分成功","");
        }
        catch (Exception e)
        {
            log.error("导出统计积分时出错",e.getMessage());
            return ReturnMessage.getReturnMessage(-1,e.getMessage(),"");
        }
    }
}
