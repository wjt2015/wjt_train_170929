package com.qunar.fresh2017.controller;

import com.qunar.fresh2017.model.AdministratorModel;
import com.qunar.fresh2017.service.impl.AdministratorServiceImpl;
import com.qunar.fresh2017.support.ReturnMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 接收前端命令，执行管理员的查询、添加、删除；
 *
 * @author
 */
@Controller
@Slf4j
public class AdministratorController {
    @Autowired
    private AdministratorServiceImpl administratorService;

    /**
     * 查询所有的系统管理员和普通管理员，发送给前端以供显示
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/showSysAndPlainAdministratorList")
    public ReturnMessage showSysAndPlainAdministratorList() {
        List<AdministratorModel> administratorModelList = administratorService.selectSysAndPlainAdministrators();
        ReturnMessage returnMessage = null;
        if (administratorModelList == null) {
            returnMessage = ReturnMessage.getReturnMessage(-1, "失败", administratorModelList);
        } else {
            returnMessage = ReturnMessage.getReturnMessage(0, "成功", administratorModelList);
        }
        return returnMessage;
    }

    /**
     * 查询所有的普通管理员，发送给前端以供显示
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/showPlainAdministratorList")
    public ReturnMessage showPlainAdministratorList() {
        List<AdministratorModel> administratorModelList = administratorService.selectPlainAdministrators();
        ReturnMessage returnMessage = null;
        if (administratorModelList == null) {
            returnMessage = ReturnMessage.getReturnMessage(-1, "失败", administratorModelList);
        } else {
            returnMessage = ReturnMessage.getReturnMessage(0, "成功", administratorModelList);
        }
        return returnMessage;
    }

    /**
     * 根据前端发送来的管理员id删除这个管理员
     *
     * @param id 管理员id
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/deleteAdministratorById/{id}")
    public ReturnMessage deleteAdministratorById(@PathVariable Integer id, HttpServletRequest httpServletRequest) {
        int iret = administratorService.deleteAdministratorById(id);
        ReturnMessage returnMessage = null;
        if (iret <= 0) {
            returnMessage = ReturnMessage.getReturnMessage(-1, "失败", null);
        } else {
            returnMessage = ReturnMessage.getReturnMessage(0, "成功", null);
        }
        return returnMessage;
    }

    /**
     * 根据前端发送来的管理员对象插入一个管理员
     *
     * @param administratorModel 管理员对象
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/insertAdministrator")
    public ReturnMessage insertAdministrator(@RequestBody AdministratorModel administratorModel, HttpServletRequest httpServletRequest) {
        int iret = administratorService.insertAdministrator(administratorModel);
        ReturnMessage returnMessage = null;
        if (iret <= 0) {
            returnMessage = ReturnMessage.getReturnMessage(-1, "失败", null);
        } else {
            returnMessage = ReturnMessage.getReturnMessage(0, "成功", null);
        }
        return returnMessage;
    }

    /**
     * 根据前端发送来的管理员对象的id删除一个管理员
     *
     * @param administratorModel 管理员对象
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/deleteOneAdministrator")
    public ReturnMessage deleteOneAdministrator(@RequestBody AdministratorModel administratorModel, HttpServletRequest httpServletRequest) {
        int iret = administratorService.deleteOneAdministrator(administratorModel);
        ReturnMessage returnMessage = null;
        if (iret <= 0) {
            returnMessage = ReturnMessage.getReturnMessage(-1, "失败", null);
        } else {
            returnMessage = ReturnMessage.getReturnMessage(0, "成功", null);
        }
        return returnMessage;
    }
}

