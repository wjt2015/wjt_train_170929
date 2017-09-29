package com.qunar.crm.service.impl;

import com.google.common.base.Preconditions;
import com.qunar.crm.dao.EmployeeListOnDutyDao;
import com.qunar.crm.model.EmployeeListOnDuty;
import com.qunar.crm.service.EmployeeListOnDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by linux2014 on 17-7-19.
 */
@Service
public class EmployeeListOnDutyServiceImpl implements EmployeeListOnDutyService {
    @Resource
    private EmployeeListOnDutyDao employeeListOnDutyDao;

    public int insertEmployeeListOnDuty(EmployeeListOnDuty employeeListOnDuty) {
        int iret = 0;
        Preconditions.checkNotNull(employeeListOnDutyDao);
        Preconditions.checkNotNull(employeeListOnDuty);
        Preconditions.checkNotNull(employeeListOnDuty.getQtalkList());
        Preconditions.checkNotNull(employeeListOnDuty.getOnDutyModule());
        Preconditions.checkNotNull(employeeListOnDuty.getIdx());
        iret = employeeListOnDutyDao.insertEmployeeListOnDuty(employeeListOnDuty);
        return iret;
    }

    public int deleteEmployeeListOnDutyByOnDutyModule(String onDutyModule) {
        int iret = 0;
        Preconditions.checkNotNull(employeeListOnDutyDao);
        Preconditions.checkNotNull(onDutyModule);
        iret = employeeListOnDutyDao.deleteEmployeeListOnDutyByOnDutyModule(onDutyModule);
        return iret;
    }

    public List<EmployeeListOnDuty> selectEmployeeListOnDuty() {
        Preconditions.checkNotNull(employeeListOnDutyDao);
        List<EmployeeListOnDuty> employeeListOnDutyList = employeeListOnDutyDao.selectEmployeeListOnDuty();
        return employeeListOnDutyList;
    }

    public EmployeeListOnDuty selectEmployeeListOnDutyByOnDutyModule(String onDutyModule) {
        Preconditions.checkNotNull(employeeListOnDutyDao);
        Preconditions.checkNotNull(onDutyModule);
        EmployeeListOnDuty employeeListOnDuty = employeeListOnDutyDao.selectEmployeeListOnDutyByOnDutyModule(onDutyModule);
        return employeeListOnDuty;
    }

    public int updateEmployeeListOnDuty(EmployeeListOnDuty employeeListOnDuty) {
        int iret = 0;
        Preconditions.checkNotNull(employeeListOnDutyDao);
        Preconditions.checkNotNull(employeeListOnDuty);
        Preconditions.checkNotNull(employeeListOnDuty.getQtalkList());
        Preconditions.checkNotNull(employeeListOnDuty.getOnDutyModule());
        Preconditions.checkNotNull(employeeListOnDuty.getIdx());
        iret = employeeListOnDutyDao.updateEmployeeListOnDuty(employeeListOnDuty);
        return iret;
    }
}



