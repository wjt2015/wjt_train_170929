package com.qunar.fresh2017.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by lj on 17-6-22.
 */
@Slf4j
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mvc.xml","classpath:spring/spring-mybatis.xml"})
public class SumPointsControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @Before
    public void setupMockMvc() throws Exception{
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void querySumPointsTest() throws  Exception{
        ResultActions resultActions=mockMvc.perform(post("/admin/sumPoints/sumPointsByCondition")
                .param("order","desc")
                .param("sort","rtx_id")
                .param("pageIndex","1")
                .param("pageNum","10")
//                .param("rtxId","liujunlj.liu")
                .accept(MediaType.ALL));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(print());
        String result=resultActions.andReturn().getResponse().getContentAsString();
        log.info(result);
    }
    @Test
    public void queryEmployeePointsTest() throws  Exception{
        ResultActions resultActions=mockMvc.perform(post("/admin/sumPoints/employeePointsByCondition")
                .param("order","desc")
                .param("sort","rtx_id")
                .param("pageIndex","1")
                .param("pageNum","10")
                .param("rtxId","liujunlj.liu")
                .param("pointsUpper","10")
                .accept(MediaType.ALL));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(print());
        String result=resultActions.andReturn().getResponse().getContentAsString();
        log.info(result);
    }
    @Test
    public void saveEmployeePointsTest() throws Exception{
        ResultActions resultActions=mockMvc.perform(post("/admin/sumPoints/insertPromotionItem")
                .param("promotionItem","晋级成功")
                .param("userId","2")
                .param("points","10")
                .param("date","2017-06-25 00:00:00")
                .param("remark","good")
                .param("operator","张雅亚")
                .accept(MediaType.ALL));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(print());
        String result=resultActions.andReturn().getResponse().getContentAsString();
        log.info(result);
    }
    @Test
    public void exportEmployeePointsTest() throws  Exception{
        ResultActions resultActions=mockMvc.perform(post("/admin/sumPoints/exportEmployeePoints")
                .param("rtxId","ming.li")
                .accept(MediaType.ALL));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(print());
        String result=resultActions.andReturn().getResponse().getContentAsString();
        log.info(result);
    }
    @Test
    public void exportSumPointsTest() throws  Exception{
        ResultActions resultActions=mockMvc.perform(post("/admin/sumPoints/exportSumPoints")
//                .param("rtxId","ming.li")
                .accept(MediaType.ALL));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(print());
        String result=resultActions.andReturn().getResponse().getContentAsString();
        log.info(result);
    }
}

