/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.controller;

import javaweb.service.LoginUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author jintao.wang  Date: 17-10-8 Time: 下午9:03
 */
@Controller
public class LoginController {
    @Resource
    private LoginUserService loginUserService;

    @RequestMapping(value = {"*.login"})
    @ResponseBody
    public String login(String name,String password){
        String result = "";
        return result;
    }
}
    