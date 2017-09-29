package com.qunar.fresh2017.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lihuiminli
 * @create 2017-06-26 下午1:01
 */
@Controller
@RequestMapping("/admin")
public class PageController {

    @RequestMapping(value = "/pointDetail")
    public String pointDetail(){
        return "pointDetail";
    }
}
