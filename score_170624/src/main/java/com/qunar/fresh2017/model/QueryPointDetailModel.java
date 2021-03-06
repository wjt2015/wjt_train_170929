package com.qunar.fresh2017.model;

import lombok.Data;

/**
 * Created by lfz on 2017/6/24.
 */
@Data
public class QueryPointDetailModel {
//    private String order;//排序方式，desc或asc
//    private String sort;//被排序的列，一般是create_time
    private Integer pageIndex;//起始页数
    private Integer pageNum;//页大小，需求中是20条一页
    private String rtxId;//qtalk，例如：fuzhi.lai
    private String jobCode;//岗位编码，例如：dev
    private String dep1;//一级部门，例如：平台事业部
    private String startDate;//开始时间，例如：2017-06-06
    private String endDate;//结束时间，例如：2017-06-06
    private String pointItemOrPromotionItem;//积分项或者晋级项，例如：BU分享、晋级成功
    private String mainContent;//主要内容
}
