package wjt;

import lombok.extern.slf4j.Slf4j;
import wjt.model.TextDiff;
import wjt.service.impl.TextDiffServiceImpl;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by linux2014 on 17-6-23.
 */
//@Slf4j
//public class Main {
//    @Resource(name = "textDiffService")
//    private static TextDiffServiceImpl textDiffService;
//
//    public static void func1(){
//        TextDiff textDiff = new TextDiff(new Integer(0),new Date(),"ab=cd","ce=ef","-ab=cd;+ce=ef");
//        log.info("textDiff={};",textDiff);
//        int iret = textDiffService.insertTextDiff(textDiff);
//        log.info("textDiff={};",textDiff);
//        log.info("iret={}",iret);
//    }
//    public static void main(String[] args){
//        func1();
//    }
//}

