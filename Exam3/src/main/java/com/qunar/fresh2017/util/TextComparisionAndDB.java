package com.qunar.fresh2017.util;

import com.qunar.fresh2017.model.TextDiff;
import com.qunar.fresh2017.dao.TextDiffDao;

import java.io.File;
import java.util.Map;

/**
 * Created by linux2014 on 17-6-16.
 */
/*TextComparisionAndDB类负责对比文本并存储对比记录*/
public class TextComparisionAndDB {
    public void comparisonAndDBFunc(File srcFile,File targetFile){
        /*读取srcFile的键值对和文本*/
        LineProcessorImp lineProcessorImp = new LineProcessorImp();
        TextReader textReader = new TextReader(lineProcessorImp,srcFile);
        textReader.readText();
        Map<String,String> srcMap = textReader.getMap();
        String srcText = textReader.getText();
        /*读取targetFile的键值对和文本*/
        lineProcessorImp = new LineProcessorImp();
        textReader = new TextReader(lineProcessorImp,targetFile);
        textReader.readText();
        Map<String,String> targetMap = textReader.getMap();
        String targetText = textReader.getText();
        /*对比两个文本的差异*/
        TextComparison textComparison = new TextComparison(srcMap, targetMap,null);
        textComparison.CompareText();
        /*将对比记录存入数据库*/
        TextDiff textDiff = new TextDiff(textComparison.getDate(),srcText,targetText,textComparison.getTextDiff());
        TextDiffDao textDiffDao = new TextDiffDao();
        int iret = textDiffDao.insertTextDiff(textDiff);
    }
}
