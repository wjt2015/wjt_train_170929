package com.qunar.fresh2017.controller;

import com.qunar.fresh2017.model.TextDiff;
import com.qunar.fresh2017.service.TextService;
import com.qunar.fresh2017.service.impl.TextServiceImpl;
import com.qunar.fresh2017.util.LoginChecker;
import com.qunar.fresh2017.util.TextComparisionAndDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

/**
 * Created by linux2014 on 17-6-16.
 */
@Controller
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private TextService textService;
    private List<TextDiff> textDiffList;
    private int beginIndex;
    private int ncount;
    private boolean isLogin;
    private LoginChecker loginChecker;

    public UserController() {
        this.textService = new TextServiceImpl();
        beginIndex = 0;
        ncount = 2;
        isLogin = false;
        String accountFname = UserController.class.getResource("/").getPath() + "account.properties";
        loginChecker = new LoginChecker(accountFname);
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(String name,String password){
        if(loginChecker != null && loginChecker.isLogin(name,password)){
            isLogin = true;
        }
        return showUploadView();
    }
    @RequestMapping(value="/upload",method=RequestMethod.GET)
    public ModelAndView showUploadView(){
        ModelAndView mv= new ModelAndView();
        mv = showModelAndView(mv);
        mv.setViewName("uploadPost");
        return mv;
    }

    @RequestMapping(value = "/uploadPost", method = RequestMethod.POST)
    public ModelAndView processFile(MultipartFile source, MultipartFile target) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("uploadPost");
        if(source == null || target == null){
            return mv;
        }
        /*读取文件,对比差异,存入数据库*/
        File srcFile = new File("srcText.txt");
        File targetFile = new File("targetText.txt");
        try {
            source.transferTo(srcFile);
            target.transferTo(targetFile);
        } catch (IOException e) {
            LOGGER.error("IOException happens!!",e);
        }
        /*对比文件差异并存入数据库*/
        TextComparisionAndDB textComparisionAndDB = new TextComparisionAndDB();
        textComparisionAndDB.comparisonAndDBFunc(srcFile,targetFile);
        mv = showModelAndView(mv);
        return mv;
    }

    @RequestMapping(value = "/prePage",method = RequestMethod.GET)
    public ModelAndView prePage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("uploadPost");
        if(beginIndex >= ncount){
            beginIndex -= ncount;
        }
        else{
            beginIndex = 0;
        }
        mv = showModelAndView(mv);
        return mv;
    }

    @RequestMapping(value = "/nextPage",method = RequestMethod.GET)
    public ModelAndView nextPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("uploadPost");
        if (textDiffList.size() == (ncount + 1)){
            beginIndex += ncount;
        }
        mv = showModelAndView(mv);
        return mv;
    }
    @RequestMapping(value = "/delete/{id}")
    public ModelAndView deleteDiffTextById(@PathVariable int id){
        textService.deleteTextDiffById(id);
        ModelAndView mv= showUploadView();
        LOGGER.info("public ModelAndView deleteDiffTextById()");
        return mv;
    }
    /*处理页面数据展示和上一页、显示更多按钮的问题*/
    private ModelAndView showModelAndView(ModelAndView mv){
        textDiffList = textService.selectTextDiffRangeByDate(beginIndex,ncount + 1);
        mv.addObject("isLogin",isLogin);
        mv.addObject("hasPrevPage",(beginIndex > 0? true : false));
        if(textDiffList.size() == (ncount + 1)){
            mv.addObject("hasNextPage",true);
            mv.addObject("textDiffList",textDiffList.subList(0,ncount));
        }
        else{
            mv.addObject("hasNextPage",false);
            mv.addObject("textDiffList",textDiffList);
        }
        return mv;
    }
}