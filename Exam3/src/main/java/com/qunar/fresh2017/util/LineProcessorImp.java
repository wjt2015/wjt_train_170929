package com.qunar.fresh2017.util;


import com.google.common.io.LineProcessor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by linux2014 on 17-6-13.
 */
public class LineProcessorImp implements LineProcessor<String>{
    private Pattern pattern;
    private Map<String,String> map;
    private String text;
    /*添加换行符<br/>，便于在页面上显示*/
    private String newLine;

    public LineProcessorImp(String regex,String newLine){
        pattern = Pattern.compile(regex);
        map = new HashMap<String, String>();
        text = new String();
        this.newLine = newLine;
    }

    public LineProcessorImp(){
        pattern = Pattern.compile("=");
        map = new HashMap<String, String>();
        text = new String();
        newLine = "<br/>";
    }

    public boolean processLine(String line){
        /*本题要求每一行的格式都是"<k>=<v>"，所以
        * 需要检测每一行文本的格式，每一行文本至少有三个字符，
        * '='字符必须不在两端*/
        int index = 0,length = 0;
        if(line == null || (length = line.length()) < 3){
            return false;
        }
        else{
            index = line.indexOf("=");
            if(index <=0 || index >= (line.length()-1))
                return false;

            String[] kv = pattern.split(line);
            map.put(kv[0],kv[1]);
            text += (line + newLine);
        }
        return true;
    }

    public String getResult(){
        return text;
    }

    public Map<String,String> getMap(){
        return map;
    }
}
