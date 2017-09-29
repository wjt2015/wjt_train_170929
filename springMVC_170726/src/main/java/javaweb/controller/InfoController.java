/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jintao.wang  Date: 17-9-15 Time: 下午2:31
 */
@Controller
public class InfoController {

    @RequestMapping(value = {"/a.htm"})
    public ModelAndView getModelAndView(){
        ModelAndView mv = new ModelAndView("a");
        return mv;
    }
}
    