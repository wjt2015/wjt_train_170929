package com.qunar.fresh2017.mapper;

import com.qunar.fresh2017.model.TextDiff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by linux2014 on 17-6-13.
 */
public interface TextDiffMapper {
    int insertTextDiff(TextDiff textDiff);
    List<TextDiff> selectTextDiff();
    /*查看最近的n条对比记录*/
    List<TextDiff> selectTextDiffLatest(@Param("n")Integer n);
    /*查看最近的记录，按时间逆序排序，从startIndex开始，记录个数为n*/
    List<TextDiff> selectTextDiffRangeByDate(@Param("startIndex")Integer startIndex,@Param("n")Integer n);

    int deleteTextDiffById(@Param("id")Integer id);
}
