package com.qunar.fresh2017.service.impl;

import com.qunar.fresh2017.model.AdministratorModel;
import com.qunar.fresh2017.service.AdministratorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by linux2014 on 17-6-24.
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/spring-mybatis.xml"})
public class AdministratorServiceImplTest {
    @Autowired
    private AdministratorServiceImpl administratorService;

    @After
    public void destroy(){
        administratorService = null;
    }

    @Test
    public void insertAdministratorTest(){
        Integer id = 0;
        Date date = new Date();
        String rtxId = "ttz" + date.getTime();
        String cn = "张宇Billy";
        String sn = "Q138477";
        String jobCode = "super_dev";
        String leader = "Clinton2";
        String hireType = "正式";
        String dep1 = "大住宿事业部";
        String dep2 = "技术产品部";
        String dep3 = "酒店技术中心";
        String dep4 = "搜索排序技术部";
//        String dep5 = "搜索技术";
        String dep5 = "分布式搜索技术研发中心";
        Integer role = 1;
        Date createTime = date;
        AdministratorModel administratorModel = new AdministratorModel(id,
                rtxId,cn,sn,jobCode,leader,hireType,dep1,dep2,dep3,dep4,dep5,
                role,createTime);
        int iret = administratorService.insertAdministrator(administratorModel);
        log.info("administrator={}",administratorModel);
        log.info("iret={}",iret);
    }
    @Test
    public void selectSysAndPlainAdministratorsTest(){
        List<AdministratorModel> administratorModelList = administratorService.selectSysAndPlainAdministrators();
        log.info("administratorModelList={}",administratorModelList);
    }
    @Test
    public void selectAdministratorByRtxIdTest(){
        log.info("测试selectRoleByRtxIdNotDeleted-------");
        String rtxId = "rtxId5";
        AdministratorModel administratorModel = administratorService.selectAdministratorByRtxId(rtxId);
        log.info("rtxId={},administratorModel={}",rtxId,administratorModel);
        rtxId = "rtxId51";
        administratorModel = administratorService.selectAdministratorByRtxId(rtxId);
        log.info("rtxId={},administratorModel={}",rtxId,administratorModel);
    }
    @Test
    public void updateAdministratorModelByIdTest(){
        log.info("void updateAdministratorModelByIdTest()----");
        AdministratorModel administratorModel = null;
        log.info("administratorModel={}",administratorModel);
        int iret = administratorService.updateAdministratorById(administratorModel);
        log.info("iret={}",iret);

        administratorModel = new AdministratorModel();
        administratorModel.setRtxId("Rtx9");
        administratorModel.setId(new Integer(3));
        log.info("administratorModel={}",administratorModel);
        iret = administratorService.updateAdministratorById(administratorModel);
        log.info("iret={}",iret);

        administratorModel.setRtxId(null);
        administratorModel.setDep1("大住宿事业部");
        log.info("administratorModel={}",administratorModel);
        iret = administratorService.updateAdministratorById(administratorModel);
        log.info("iret={}",iret);

        administratorModel.setId(new Integer(5));
        administratorModel.setCn("李小龙");
        administratorModel.setDep1("酒店事业部");
        administratorModel.setHireType("dev");
        administratorModel.setRole(new Integer(2));
        log.info("administratorModel={}",administratorModel);
        iret = administratorService.updateAdministratorById(administratorModel);
        log.info("iret={}",iret);
    }
    @Test
    public void selectAdministratorByIdTest(){
        AdministratorModel administratorModel = null;
        Integer id = 1;
        administratorModel = administratorService.selectAdministratorById(id);
        log.info("id={},administratorModel={}",id,administratorModel);
        id = null;
        administratorModel = administratorService.selectAdministratorById(id);
        log.info("id={},administratorModel={}",id,administratorModel);
    }
}

