package com.qunar.frc.demo4.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qunar.frc.demo4.util.DateTimeJsonSerializer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 酒店预定信息
 */
public class HotelBookInfoModel {
    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 购买的酒店id
     */
    private Integer hotelId;
    /**
     * 入住人
     */
    private String checkInUser;
    /**
     * 入住时间
     */
    private Date checkInDate;
    /**
     * 下单时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getCheckInUser() {
        return checkInUser;
    }

    public void setCheckInUser(String checkInUser) {
        this.checkInUser = checkInUser;
    }

    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
