package com.qunar.fresh2017.util;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by linux2014 on 17-6-13.
 */
/*TextComparison用于实现文件对比功能*/
public class TextComparison {
    /*date记录对比时间*/
    private Date date;
    private Map<String,String> textSrc;
    private Map<String,String> textTarget;
    private List<String> textDiffList;
    private String textDiff;
    private String newLine;


    public TextComparison(Map<String, String> textSrc, Map<String, String> textTarget,String newLine) {
        this.textSrc = textSrc;
        this.textTarget = textTarget;
        textDiffList = new LinkedList<String>();
        textDiff = new String();
        this.newLine = (newLine == null?"<br/>":newLine);
    }

    /*对比文件textSrc和textTarget，将结果存储在textDiffList内*/
    public void CompareText(){
        /*记录对比时间*/
        date = new Date();
        for(Map.Entry<String,String> entry:textSrc.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            String diff = null;
            if(textTarget.containsKey(key)){
                String valueTarget = textTarget.get(key);
                if(!value.equals(valueTarget)){
                    diff = "-" + key + "=" + value + ";+" + key + "=" + valueTarget;
                }
                /*删除源文件和目标文件共有的key，避免干扰后面查找目标文件新增的key*/
                textTarget.remove(key);
            }
            else{
                diff = "-" + key + "=" + value;
            }
            if(diff != null){
                textDiffList.add(diff);
            }
        }
        for(Map.Entry<String,String> entry:textTarget.entrySet()){
            String diff ="+" + entry.getKey() + "=" + entry.getValue();
            textDiffList.add(diff);
        }

        textDiff = new String();
        for(String strLine:textDiffList){
            textDiff +=(strLine + newLine);
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, String> getTextSrc() {
        return textSrc;
    }

    public void setTextSrc(Map<String, String> textSrc) {
        this.textSrc = textSrc;
    }

    public Map<String, String> getTextTarget() {
        return textTarget;
    }

    public void setTextTarget(Map<String, String> textTarget) {
        this.textTarget = textTarget;
    }

    public List<String> getTextDiffList() {
        return textDiffList;
    }

    public void setTextDiffList(List<String> textDiffList) {
        this.textDiffList = textDiffList;
    }

    public String getTextDiff() {
        return textDiff;
    }
}
