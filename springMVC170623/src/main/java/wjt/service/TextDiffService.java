package wjt.service;

import org.apache.ibatis.annotations.Param;
import wjt.model.TextDiff;

import java.util.Date;

/**
 * Created by linux2014 on 17-6-23.
 */
public interface TextDiffService {
    int insertTextDiff(TextDiff textDiff);
    int updateTextDiffByDate(TextDiff textDiff, Date date);
    TextDiff selectTextDiffById(Integer id);
}
