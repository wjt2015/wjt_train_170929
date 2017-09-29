package com.qunar.fresh2017.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lj on 17-6-25.
 */
@ToString
@Setter
@Getter
public class SumPointsExportConditionModel {
    //qtalk，与大讲堂数据表名称一致，例如：wangliang.wang
    private String rtxId;
    //职位编码，例如：dev,qa
    private String jobCode;
    //一级部门
    private String dep1;
    //分数为0.5的倍数，可以为正，可以为负数,最大分数99.5
    private BigDecimal pointsFloor;
    //分数为0.5的倍数，可以为正，可以为负数,最大分数99.5
    private BigDecimal pointsUpper;
    //精确到天，默认为今天
    private Date dateStart;
    //精确到天，默认为今天
    private Date dateEnd;
}
