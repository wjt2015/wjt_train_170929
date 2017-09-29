package com.qunar.crm.service;

import com.qunar.crm.model.EmployeeListOnDuty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by linux2014 on 17-7-19.
 */
public interface EmployeeListOnDutyService {
    int insertEmployeeListOnDuty(EmployeeListOnDuty employeeListOnDuty);
    int deleteEmployeeListOnDutyByOnDutyModule(String onDutyModule);
    List<EmployeeListOnDuty> selectEmployeeListOnDuty();
    EmployeeListOnDuty selectEmployeeListOnDutyByOnDutyModule(String onDutyModule);
    int updateEmployeeListOnDuty(EmployeeListOnDuty employeeListOnDuty);
}
