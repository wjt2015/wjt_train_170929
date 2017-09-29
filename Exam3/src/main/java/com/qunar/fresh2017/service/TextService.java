package com.qunar.fresh2017.service;

import com.qunar.fresh2017.model.TextDiff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by linux2014 on 17-6-18.
 */
public interface TextService {
    /*查看最近的n条对比记录*/
    List<TextDiff> selectTextDiffLatest(Integer n);
    /*查看最近的记录，按时间逆序排序，从startIndex开始，记录个数为n*/
    List<TextDiff> selectTextDiffRangeByDate(Integer startIndex,Integer n);
    int deleteTextDiffById(Integer id);
}
