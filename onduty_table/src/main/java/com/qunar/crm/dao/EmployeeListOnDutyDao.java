package com.qunar.crm.dao;

import com.qunar.crm.model.EmployeeListOnDuty;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by linux2014 on 17-7-18.
 */
@Repository
public interface EmployeeListOnDutyDao {
    int insertEmployeeListOnDuty(EmployeeListOnDuty employeeListOnDuty);
    int deleteEmployeeListOnDutyByOnDutyModule(@Param("onDutyModule")String onDutyModule);
    List<EmployeeListOnDuty> selectEmployeeListOnDuty();
    EmployeeListOnDuty selectEmployeeListOnDutyByOnDutyModule(@Param("onDutyModule")String onDutyModule);
    int updateEmployeeListOnDuty(EmployeeListOnDuty employeeListOnDuty);
}


