package wjt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import wjt.model.TextDiff;
import wjt.service.TextDiffService;

import java.util.Date;


/**
 * Created by linux2014 on 17-6-22.
 */
@Slf4j
@Controller
public class HelloReaderController {
    @Autowired
    private TextDiffService textDiffService;

    @RequestMapping(value = "/hello")
    public ModelAndView getHello(){
        ModelAndView mv = new ModelAndView();
        TextDiff textDiff = textDiffService.selectTextDiffById(48);
        mv.addObject("message","Hello,Readers!!spring入门经典");
        mv.addObject("textDiff",textDiff);
        mv.setViewName("addTextDiff");
        log.info("---getHello()");
        return mv;
    }

    @RequestMapping(value="/addTextDiff",method = RequestMethod.POST)
    public ModelAndView addTextDiff(@ModelAttribute("form")TextDiff textDiff){
        ModelAndView mv = new ModelAndView();
        textDiff.setDate(new Date());
        log.info("addTextDiff();1;textDiff={}",textDiff);
        textDiffService.insertTextDiff(textDiff);
        log.info("addTextDiff();2;textDiff={}",textDiff);
        mv.setViewName("helloReader");
        return mv;
    }
}
