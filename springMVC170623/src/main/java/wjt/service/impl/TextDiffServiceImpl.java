package wjt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wjt.mapper.TextDiffMapper;
import wjt.model.TextDiff;
import wjt.service.TextDiffService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by linux2014 on 17-6-23.
 */
//@Service
@Service("textDiffService")
public class TextDiffServiceImpl implements TextDiffService {
//    @Autowired
    @Resource(name = "textDiffMapper")
    private TextDiffMapper textDiffMapper;

    @Override
    public int insertTextDiff(TextDiff textDiff) {
        int iret = textDiffMapper.insertTextDiff(textDiff);
        return iret;
    }
    @Override
    public int updateTextDiffByDate(TextDiff textDiff, Date date){
        int iret = textDiffMapper.updateTextDiffByDate(textDiff.getDate(),textDiff.getSrcText(),textDiff.getTargetText(),textDiff.getDiffText(),date);
        return iret;
    }
    @Override
    public TextDiff selectTextDiffById(Integer id){
        return textDiffMapper.selectTextDiffById(id);
    }
}

