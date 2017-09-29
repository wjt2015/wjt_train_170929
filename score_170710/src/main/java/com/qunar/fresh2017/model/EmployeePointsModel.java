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
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 用户表主键
     */
    private Integer userId;
    /**
     * 积分项或者晋级项，例如：bu分享/晋级成功
     */
    private String pointItemOrPromotionItem;
    /**
     * 分数为0.5的倍数，可以为正，可以为负数,最大分数99.5
     */
    private BigDecimal points;
    /**
     * 主要内容
     */
    private String mainContent;
    /**
     * 例如：OFC2017-2期
     */
    private String projectPeriod;
    /**
     * 精确到天，默认为今天
     */
    private Date date;
    /**
     * 学员人数，选填项
     */
    private Integer personNum;
    /**
     * 学员评分，选填项，只能输入大于等于0小数点后一位的数字
     */
    private BigDecimal score;
    /**
     * 1小时、2小时（默认）、一个月、三个月、其它
     */
    private String duration;
    /**
     * 地点
     */
    private String location;
    /**
     * 如果积分数有扣分，则备注为必填项（需要在前台、后台进行检查）
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 对应用户表中的cn
     */
    private String operator;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 0:未删除（默认值），1：已删除
     */
    private Integer isDeleted;
}
