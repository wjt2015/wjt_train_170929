package com.qunar.fresh2017.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工积分表对应的model
 */
@Getter
@Setter
@ToString
public class EmployeePointsModel {
    private Integer id;//自增主键
    private String rtxId;
    private Integer userId;//用户表主键，代表该条积分详情属于哪个用户
    private String pointItemOrPromotionItem;//积分项或者晋级项，例如：bu分享/晋级成功
    private BigDecimal points;//积分，分数为0.5的倍数，可以为正，可以为负数,最大分数99.5
    private String mainContent;//主要内容
    private String projectPeriod;//项目批次，例如：QFC2017-2期
    private Date date;//时间
    private Integer personNum;//学员人数，选填项
    private BigDecimal score;// 学员评分，选填项，只能输入大于等于0小数点后一位的数字
    private String duration;//时长，1小时、2小时（默认）、一个月、三个月、其它
    private String location;//地点，例如：东升培4
    private String remark;//如果积分数有扣分，则备注为必填项（需要在前台、后台进行检查）
    private Date createTime;//创建时间
    private String operator;//操作人，对应用户表中的cn
    private Date updateTime;//更新时间
    private Integer isDeleted;//逻辑删除标志位，0:未删除（默认值），1：已删除
}
