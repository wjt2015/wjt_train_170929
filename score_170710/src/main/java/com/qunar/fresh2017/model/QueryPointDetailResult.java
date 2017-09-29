package com.qunar.fresh2017.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lfz on 2017/6/24.
 */
@Data
public class QueryPointDetailResult {
    private String cn;//姓名，对应qtalk的姓名，是唯一的，例如：张宇Billy
    private String rtxId;//qtalk，例如：fuzhi.lai
    private String jobCode;//岗位编码，例如：dev
    private String leader;//主管，例如：陆艳
    private String dep1;//一级部门，例如：平台事业部
    private String pointItemOrPromotionItem;//积分项或者晋级项，例如：BU分享、晋级成功
    private String mainContent;//主要内容
    private String projectPeriod;//项目批次，例如：2017-2期
    private String duration;//时长，例如：2小时
    private String location;//地点，例如：东升培4
    private Date date;//时间，例如2017-06-06
    private Integer personNum;//人数，例如：45
    private BigDecimal score;//学员评分，例如：4.5
    private BigDecimal points;//积分，例如1.5
    private String remark;//关键评价/备注说明，积分得不到满分，则必须填写
    private String operator;//操作人(对应大讲堂人员表的cn)，例如：谢文泉
    private Date updateTime;//更新时间

}
