package com.qunar.fresh2017.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 晋级分数和积分项表对应的model
 */
@Getter
@Setter
@ToString
public class PromotionAndPointItemModel {
    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 晋级记录,例如:晋级成功、晋级失败;积分项，例如：应届生培训讲师
     */
    private String promotionOrPointItem;
    /**
     * 分数必须大于等于0
     */
    private BigDecimal score;
    /**
     * 0：积分项，1：晋级分数，默认0
     */
    private Integer type;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
