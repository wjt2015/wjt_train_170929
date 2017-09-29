package com.qunar.crm.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by linux2014 on 17-7-19.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring_mybatis.xml"})
public class EmployeeListOnDutyService2Test {
    @Autowired
    private EmployeeListOnDutyService2 employeeListOnDutyService2;

    @Test
    public void addOneEmployeeOnDutyTest(){
        int iret = 0;
        String qtalkId = "san2.zhang";
        iret = employeeListOnDutyService2.addOneEmployeeOnDuty(qtalkId);
        log.info("iret = {}",iret);
    }
    @Test
    public void deleteOneEmployeeOnDutyTest(){
        String qtalkId = "san2.zhang";
        int iret = employeeListOnDutyService2.deleteOneEmployeeOnDuty(qtalkId);
        log.info("iret={}",iret);
    }
    @Test
    public void updateEmployeeOnDutyTest(){
        String qtalkId = "bama.ao";
        int iret = employeeListOnDutyService2.updateEmployeeOnDuty(qtalkId);
        log.info("iret={}",iret);
    }
    @Test
    public void queryEmployeeOnDutyTest(){
        List<String> qtalkList = null;
        int[] idxArr = new int[1];
        idxArr[0] = -1;
        qtalkList = employeeListOnDutyService2.queryEmployeeOnDuty(idxArr);
        log.info("qtalkList={};idx={}",qtalkList,idxArr[0]);
    }
    @Test
    public void updateNextIdxTest(){
        int nextIdx = 0;
        nextIdx = employeeListOnDutyService2.updateNextIdx();
        log.info("nextId={}",nextIdx);
    }
}

