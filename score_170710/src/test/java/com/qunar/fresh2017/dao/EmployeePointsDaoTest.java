package com.qunar.fresh2017.dao;

import com.qunar.fresh2017.HelloAction;
import com.qunar.qtown.service.RtxUserManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by lfz on 2017/6/21.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/spring-mybatis.xml", "classpath:spring/spring-mvc.xml"})
public class EmployeePointsDaoTest {
    @Autowired
    private EmployeePointsDao employeePointsDao;
//
//    @Autowired
//    private HelloAction helloAction;
//    @Test
//    public void testGetCount() throws Exception {
//        helloAction.sayHello();
////        System.out.println(employeePointsDao.getCount());
//    }

}
