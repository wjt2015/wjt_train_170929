package com.qunar.fresh2017.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Created by lj on 17-6-22.
 */
@Getter
@Setter
@ToString
public class SumPointsResultModel {
    //qtalk，与大讲堂数据表名称一致，例如：wangliang.wang
    private String rtxId;
    //姓名,在职员工中唯一标识符, 与大讲堂数据表名称一致，例如：张宇Billy
    private String cn;
    //职位编码，例如：dev,qa
    private String jobCode;
    //leader的名字 : 陆艳
    private String leader;
    //用工类型，正式/实习
    private String hireType;
    //一级部门
    private String dep1;
    // 二级部门
    private String dep2;
    //三级部门
    private String dep3;
    // 四级部门
    private String dep4;
    //五级部门
    private String dep5;
    //统计积分
    private BigDecimal countPoints;
}
