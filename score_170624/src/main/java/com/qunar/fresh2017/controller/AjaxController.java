package com.qunar.fresh2017.controller;

import com.qunar.fresh2017.model.AdministratorModel;
import com.qunar.fresh2017.service.impl.AdministratorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by linux2014 on 17-6-30.
 */
@Slf4j
@Controller
@ResponseBody
public class AjaxController {
    @Autowired
    private AdministratorServiceImpl administratorService;

    @RequestMapping("/info")
    public AdministratorModel getInfo(){
        AdministratorModel model = new AdministratorModel();
        model.setId(1);
        model.setHireType("正式");
        return model;
    }

    @RequestMapping("/getNAdministrators")
    public List<AdministratorModel> getAdministratorList(@RequestBody AdministratorModel admin){
        List<AdministratorModel> administratorModelList = new LinkedList<AdministratorModel>();
        AdministratorModel administratorModel = new AdministratorModel();
        administratorModel.setHireType(admin.getHireType());
        administratorModelList.add(administratorModel);
        administratorModel = new AdministratorModel();
        administratorModel.setCn(admin.getCn());
        administratorModelList.add(administratorModel);
        log.info("administratorModelList={}" + administratorModelList);
        return administratorModelList;
    }

    @RequestMapping("/getAdministratorByIds")
    public List<AdministratorModel> getAdministratorByIds(@RequestBody Integer[] ids){
        List<AdministratorModel> administratorModelList = new LinkedList<AdministratorModel>();
        AdministratorModel administratorModel = null;
        log.info("ids=" + ids);
        if(ids != null){
            for(Integer id:ids){
                administratorModel = administratorService.selectAdministratorById(id);
                if(administratorModel != null){
                    administratorModelList.add(administratorModel);
                }
            }
        }
        return administratorModelList;
    }
    @RequestMapping("/getUsers")
    public List<AdministratorModel> getUsers(@RequestBody Integer[] ids){
        List<AdministratorModel> administratorModelList = new LinkedList<AdministratorModel>();
        AdministratorModel administratorModel = null;
        for (Integer id:ids){
            administratorModel = administratorService.selectAdministratorById(id);
            if(administratorModel != null){
                administratorModelList.add(administratorModel);
            }
        }
        return administratorModelList;
    }
    @RequestMapping("/insertUser")
    public AdministratorModel insertUser(@RequestBody AdministratorModel administratorModel){
        log.info("administratorModel:" + administratorModel);
        int iret = administratorService.insertAdministrator(administratorModel);
        log.info("iret=" + iret);
        return administratorModel;
    }
    @RequestMapping("/getAdministratorById")
    public AdministratorModel getAdministratorById(@RequestBody Integer id){
        AdministratorModel administratorModel = null;
        log.info("id=" + id);
        if(id != null){
            administratorModel = administratorService.selectAdministratorById(id);
            log.info("administratorModel=" + administratorModel);
        }
        return administratorModel;
    }
}
