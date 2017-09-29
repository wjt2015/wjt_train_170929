package com.qunar.fresh2017.service.impl;

import com.qunar.fresh2017.dao.TextDiffDao;
import com.qunar.fresh2017.model.TextDiff;
import com.qunar.fresh2017.service.TextService;

import java.util.List;

/**
 * Created by linux2014 on 17-6-18.
 */
public class TextServiceImpl implements TextService {
    private TextDiffDao textDiffDao;

    public TextServiceImpl() {
        this.textDiffDao = new TextDiffDao();
    }

    public List<TextDiff> selectTextDiffLatest(Integer n) {
        return textDiffDao.selectTextDiffLatest(n);
    }

    /*查看最近的记录，按时间逆序排序，从startIndex开始，记录个数为n*/
    public List<TextDiff> selectTextDiffRangeByDate(Integer startIndex,Integer n){
        return textDiffDao.selectTextDiffRangeByDate(startIndex,n);
    }
    public int deleteTextDiffById(Integer id){
        return textDiffDao.deleteTextDiffById(id);
    }
}
