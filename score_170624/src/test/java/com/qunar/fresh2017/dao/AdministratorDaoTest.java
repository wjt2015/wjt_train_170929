package com.qunar.fresh2017.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by lfz on 2017/6/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/spring-mybatis.xml", "classpath:spring/spring-mvc.xml"})
public class AdministratorDaoTest {

    @Autowired
    private AdministratorDao dao;
    @Test
    public void testSelectAdministratorByRtxIdNotDeleted() throws Exception {
        System.out.println(dao.selectAdministratorByRtxId("fuzhi.lai"));
    }
}