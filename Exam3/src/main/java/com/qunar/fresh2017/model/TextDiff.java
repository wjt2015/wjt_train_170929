package com.qunar.fresh2017.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by linux2014 on 17-6-13.
 */
public class TextDiff {

    private Integer id;
    /*以下四个成员用于存取数据库，也是为了在页面上显示*/
    private Date date;
    private String srcText;
    private String targetText;
    private String diffText;

    public TextDiff() {
    }

    public TextDiff(Date date, String srcText, String targetText, String diffText) {
        this.date = date;
        this.srcText = srcText;
        this.targetText = targetText;
        this.diffText = diffText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSrcText() {
        return srcText;
    }

    public void setSrcText(String srcText) {
        this.srcText = srcText;
    }

    public String getTargetText() {
        return targetText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }

    public String getdiffText() {
        return diffText;
    }

    public void setdiffText(String diffText) {
        this.diffText = diffText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString(){
        String objStr =date + ";" + srcText + ";" +
                targetText + ";" + diffText + "}";
        objStr = "{" + objStr;
        return objStr;
    }
}
