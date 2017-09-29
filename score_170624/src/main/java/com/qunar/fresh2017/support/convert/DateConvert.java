package com.qunar.fresh2017.support.convert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lj on 17-6-24.
 */
@Slf4j
@Component
public class DateConvert implements Converter<String, Date> {
    @Override
    public Date convert(String stringDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            log.error("日期转换错误",e.getMessage(),stringDate);
        }
        return null;
    }

}
