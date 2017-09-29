package com.qunar.fresh2017.controller;

import com.qunar.fresh2017.model.AdministratorModel;
import com.qunar.fresh2017.service.impl.AdministratorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author lihuiminli
 * @create 2017-06-21 下午3:05
 */
@Slf4j
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-mybatis.xml", "classpath*:spring/spring-mvc.xml"})
public class PromotionAndPointItemControllerTest {

    @Resource
    private WebApplicationContext webApplicationContext;
    @Resource
    private AdministratorServiceImpl administratorService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void saveTest(){
        Integer id = 0;
        Date date = new Date();
        String rtxId = "lihuimin.li";
        String cn = "张宇Billy";
        String sn = "Q138477";
        String jobCode = "super_dev";
        String leader = "中共中央总书记";
        String hireType = "正式";
        String dep1 = "大住宿事业部";
        String dep2 = "技术产品部";
        String dep3 = "酒店技术中心";
        String dep4 = "搜索排序技术部";
        String dep5 = "搜索技术";
        Integer role = 1;
        Date createTime = date;
        AdministratorModel administratorModel = new AdministratorModel(id,
                rtxId,cn,sn,jobCode,leader,hireType,dep1,dep2,dep3,dep4,dep5,
                role,createTime);
        //administratorService.insertAdministrator(administratorModel);
        administratorService.updateAdministratorById(administratorModel);
    }

    @Test
    public void savePromotionOrPointItemTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/admin/addPromotionOrPointItem")
                .param("promotionOrPointItem", "应届生培训讲师222")
                .param("score", "2.0")
                .param("remark", "根据授课要求按质按量完成授课并且达成教学目标； \n" +
                        "每批次培训完毕后，需要批改至少5份作业了解自己的授课效果，课程的反馈合格积分即可生效,一次课程（2小时）2分")
                .param("type", "0")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andDo(print());
        String result = resultActions.andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void deletePromotionOrPointItemTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/admin/deletePromotionOrPointItem")
                .param("id", "2")
                .param("type", "0")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andDo(print());
        String result = resultActions.andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void updatePromotionOrPointItemTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/admin/updatePromotionOrPointItem")
                .param("id", "3")
                .param("promotionOrPointItem", "2应届生培训讲师2")
                .param("score", "2.0")
                .param("remark", "根据授课要求按质按量完成授课并且达成教学目标； \n" +
                        "每批次培训完毕后，需要批改至少5份作业了解自己的授课效果，课程的反馈合格积分即可生效,一次课程（2小时）2分")
                .param("type", "0")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andDo(print());
        String result = resultActions.andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void queryPromotionOrPointItemByTypeTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/admin/queryPromotionOrPointItemByTemp")
                .param("type", "0")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andDo(print());
        String result = resultActions.andReturn().getResponse().getContentAsString();
        log.info(result);
    }


}
