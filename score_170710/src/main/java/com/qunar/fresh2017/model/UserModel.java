package com.qunar.fresh2017.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 用户表对应的model
 */
@Setter
@Getter
@ToString
public class UserModel {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * qtalk，与大讲堂数据表名称一致，唯一性约束条件,例如：wangliang.wang
     */
    private String rtxId;
    /**
     * 姓名,在职员工中唯一标识符, 与大讲堂数据表名称一致，例如：张宇Billy
     */
    private String cn;
    /**
     * 例如:Q138477
     */
    private String sn;
    /**
     * 职位编码，例如：dev,qa
     */
    private String jobCode;
    /**
     * leader的名字 : 陆艳
     */
    private String leader;
    /**
     * 用工类型，正式/实习
     */
    private String hireType;
    /**
     * 一级部门
     */
    private String dep1;
    /**
     * 二级部门
     */
    private String dep2;
    /**
     * 三级部门
     */
    private String dep3;
    /**
     * 四级部门
     */
    private String dep4;
    /**
     * 五级部门
     */
    private String dep5;
    /**
     * 1:系统管理员,2:普通管理员,3:部门管理员,4:普通用户
     */
    private Integer role;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 0:未删除（默认值），1：已删除
     */
    private Integer isDeleted;

}
