package com.qunar.fresh2017.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lj on 17-6-25.
 */
@Setter
@Getter
@ToString
public class PromotionItemModel {
    private Integer userId;//用户表主键，代表该条积分详情属于哪个用户
    private String promotionItem;//积分项或者晋级项，例如：bu分享/晋级成功
    private BigDecimal points;//积分，分数为0.5的倍数，可以为正，可以为负数,最大分数99.5
    private Date date;//时间
    private String remark;//如果积分数有扣分，则备注为必填项（需要在前台、后台进行检查）
    private String operator;//操作人，对应用户表中的cn
}
