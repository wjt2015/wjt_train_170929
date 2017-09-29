package com.qunar.frc.demo4.controller;

import com.qunar.frc.demo4.model.HotelBookInfoModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by renqun.yuan on 2016/4/5.
 */
@Controller
@RequestMapping("/hotelBookInfo")
public class HotelBookInfoController {

    @RequestMapping("/queryHotelBookById")
    @ResponseBody
    public Object queryHotelBookById(@RequestParam("id") Integer id) {
        HotelBookInfoModel hotelBookInfoModel = new HotelBookInfoModel();
        hotelBookInfoModel.setId(id);
        hotelBookInfoModel.setCheckInUser("qunar");
        hotelBookInfoModel.setCreateTime(new Date());
        hotelBookInfoModel.setCheckInDate(new Date());
        hotelBookInfoModel.setHotelId(2);
        return hotelBookInfoModel;
    }


}
