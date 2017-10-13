/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.controller;

import javaweb.service.LoginUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jintao.wang  Date: 17-10-8 Time: 下午9:03
 */
@Controller
@RequestMapping(value={"/pages"})
public class AuthController {

    public AuthController(){
        System.out.println("!!;public AuthController(),start:");
        System.out.println("!!;public AuthController(),finish!!");
    }
    @RequestMapping(value = {"/onduty_table.htm"})
    public ModelAndView getPage(HttpServletRequest httpServletRequest){
        String uri = httpServletRequest.getRequestURI();
        int length = uri.length();
        uri = uri.substring(0,length - 4);
        String viewName = uri;
        return new ModelAndView(viewName);
    }
}


    