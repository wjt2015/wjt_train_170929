package com.qunar.crm.service;

import com.google.common.base.Preconditions;
import com.qunar.crm.model.EmployeeListOnDuty;
import com.qunar.crm.service.impl.EmployeeListOnDutyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by linux2014 on 17-7-19.
 */
@Service
public class EmployeeListOnDutyService2 {
    private final String onDutyModule = "大住宿系统A的维护";/*在本项目内以该工作模块为例*/
    @Autowired
    private EmployeeListOnDutyServiceImpl employeeListOnDutyService;

    /**添加一个值班员工；执行成功返回值大于等于0，执行失败返回值为-1*/
    @Transactional
    public int addOneEmployeeOnDuty(String qtalkId){
        int iret = -1;
        Preconditions.checkNotNull(employeeListOnDutyService);
        Preconditions.checkNotNull(qtalkId);
        EmployeeListOnDuty employeeListOnDuty = employeeListOnDutyService.selectEmployeeListOnDutyByOnDutyModule(onDutyModule);
        Preconditions.checkNotNull(employeeListOnDuty);
        String qtalkList = employeeListOnDuty.getQtalkList();
        qtalkList += (";" + qtalkId);
        employeeListOnDuty.setQtalkList(qtalkList);
        iret = employeeListOnDutyService.updateEmployeeListOnDuty(employeeListOnDuty);
        return iret;
    }
    /**删除一个值班员工；执行成功返回值大于等于0，执行失败返回值为-1*/
    @Transactional
    public int deleteOneEmployeeOnDuty(String qtalkId){
        int iret = -1;
        Preconditions.checkNotNull(qtalkId);
        EmployeeListOnDuty employeeListOnDuty = employeeListOnDutyService.selectEmployeeListOnDutyByOnDutyModule(onDutyModule);
        Preconditions.checkNotNull(employeeListOnDuty);
        String qtalkList = employeeListOnDuty.getQtalkList();
        Pattern pattern = Pattern.compile(qtalkId);
        String[] strArr = pattern.split(qtalkList);
        qtalkList = "";
        if(strArr != null && strArr.length >= 1){
            if(strArr.length == 1){
                if(strArr[0].startsWith(";")){
                    qtalkList = strArr[0].substring(1);
                }
                else if(strArr[0].endsWith(";")){
                    qtalkList = strArr[0].substring(0,strArr[0].length()-1);
                }
            }
            else if(strArr.length == 2){
                strArr[0] = strArr[0].substring(0,strArr[0].length()-1);
                qtalkList = strArr[0] + strArr[1];
            }
        }
        employeeListOnDuty.setQtalkList(qtalkList);
        iret = employeeListOnDutyService.updateEmployeeListOnDuty(employeeListOnDuty);
        return iret;
    }
    /**更改一个值班员工的值班次序，使之在本轮的最后值班；执行成功返回值大于等于0，执行失败返回值为-1*/
    @Transactional
    public int updateEmployeeOnDuty(String qtalkId){
        int iret = -1;
        Preconditions.checkNotNull(employeeListOnDutyService);
        Preconditions.checkNotNull(qtalkId);
        EmployeeListOnDuty employeeListOnDuty = employeeListOnDutyService.selectEmployeeListOnDutyByOnDutyModule(onDutyModule);
        Preconditions.checkNotNull(employeeListOnDuty);
        String qtalkList = employeeListOnDuty.getQtalkList();
        Pattern pattern = Pattern.compile(qtalkId);
        String[] strArr = pattern.split(qtalkList);
        qtalkList = "";

        if(strArr != null && strArr.length >= 1){
            if(strArr.length == 1){
                if(strArr[0].startsWith(";")){
                    qtalkList = strArr[0].substring(1);
                }
                else if(strArr[0].endsWith(";")){
                    qtalkList = strArr[0].substring(0,strArr[0].length()-1);
                }
            }
            else if(strArr.length == 2){
                strArr[0] = strArr[0].substring(0,strArr[0].length()-1);
                qtalkList = strArr[0] + strArr[1];
            }
        }
        if(qtalkList.length() >= 1){
            qtalkList += (";" + qtalkId);
        }
        else {
            qtalkList = qtalkId;
        }
        employeeListOnDuty.setQtalkList(qtalkList);
        iret = employeeListOnDutyService.updateEmployeeListOnDuty(employeeListOnDuty);
        return iret;
    }
    /**
     * 每一天都要更新值班的索引
     */
    public int updateNextIdx(){
        int nowIdx = 0,nextIdx = -1,iret = 0,n = 0;
        Pattern pattern = Pattern.compile(";");
        EmployeeListOnDuty employeeListOnDuty = employeeListOnDutyService.selectEmployeeListOnDutyByOnDutyModule(onDutyModule);
        if(employeeListOnDuty != null){
            nowIdx = employeeListOnDuty.getIdx();
            String qtalkList = employeeListOnDuty.getQtalkList();
            n = pattern.split(qtalkList).length;
            if(nowIdx < (n-1)){
                nextIdx = nowIdx + 1;
            }
            else{
                nextIdx = 0;
            }
            employeeListOnDuty.setIdx(nextIdx);
            iret = employeeListOnDutyService.updateEmployeeListOnDuty(employeeListOnDuty);
        }
        return nextIdx;
    }
    /**
     * 查询当前的值班安排信息，包含值班序列和下一次的值班员工的索引，索引从0开始计算；
     * 其中值班序列用列表返回，下一次值班员工的索引用单元素的int数组返回；
     * 执行成功返回值非null，执行失败返回null*/
    public List<String> queryEmployeeOnDuty(int[] idxArr){
        List<String> qtalkList = null;
        Preconditions.checkNotNull(employeeListOnDutyService);
        EmployeeListOnDuty employeeListOnDuty = employeeListOnDutyService.selectEmployeeListOnDutyByOnDutyModule(onDutyModule);
        if(employeeListOnDuty != null){
            qtalkList = new LinkedList<String>();
            if(idxArr != null){
                idxArr[0] = employeeListOnDuty.getIdx();
            }
            Pattern pattern = Pattern.compile(";");
            String[] strArr = pattern.split(employeeListOnDuty.getQtalkList());
            for(String qtalkStr:strArr){
                qtalkList.add(qtalkStr);
            }
        }
        return  qtalkList;
    }
}





