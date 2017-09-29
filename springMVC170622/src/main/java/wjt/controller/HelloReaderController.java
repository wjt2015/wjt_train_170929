package wjt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import wjt.dao.UserDao;
import wjt.model.User;


/**
 * Created by linux2014 on 17-6-22.
 */
@Controller
public class HelloReaderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloReaderController.class);
    @Autowired
    private UserDao userDao;
//    public HelloReaderController(){
//        userDao = new UserDao();
//    }
    @RequestMapping(value = "/hello")
    public ModelAndView getHello(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message","Hello,Readers!!spring入门经典");
        mv.addObject("ti","Hello,Readers!!spring入门经典");
        mv.setViewName("AddUser");
        return mv;
    }

    @RequestMapping(value="/AddUser",method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("form")User user){
        ModelAndView mv = new ModelAndView();
        LOGGER.info("user:",user);
        userDao.insertUser(user);
        mv.setViewName("helloReader");
        return mv;
    }
}
