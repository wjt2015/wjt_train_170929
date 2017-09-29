package wjt.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wjt.model.TextDiff;

import java.util.Date;

/**
 * Created by linux2014 on 17-6-23.
 */
//@Repository
@Repository("textDiffMapper")
public interface TextDiffMapper {
    int insertTextDiff(TextDiff textDiff);
    int updateTextDiffByDate(@Param("date")Date date,@Param("srcText")String srcText,@Param("targetText")String targetText,@Param("diffText")String diffText,@Param("date2")Date date2);
    TextDiff selectTextDiffById(@Param("id")Integer id);
}
