package com.qunar.fresh2017.controller;

import com.qunar.fresh2017.model.AdministratorModel;
import com.qunar.fresh2017.service.AdministratorService;
import com.qunar.fresh2017.support.CookieUtil;
import com.qunar.fresh2017.support.ReturnMessage;
import com.qunar.security.QSSO;
import com.qunar.security.qsso.model.QUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lihuiminli
 * @create 2017-06-24 下午4:45
 */
@Slf4j
@Controller
public class LoginController {
    private static final int SYSTEM_ADMINISTRATOR = 1;
    private static final int GENERAL_ADMINISTRATOR = 2;
    @Resource
    private AdministratorService administratorService;


    /*@RequestMapping(value = "/login")
    public ReturnMessage login(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getParameter("token");
        log.info("controller里的token值为{}", token);
        QUser user = QSSO.getQUser(token);
        log.info("user的名称：{}", user);
        if (null != user) {
            String userId = user.getUserId();
            AdministratorModel administratorModel = administratorService.selectAdministratorByRtxIdNotDeleted(userId);
            int userRoleNum = administratorModel.getRole();
            if (SYSTEM_ADMINISTRATOR == userRoleNum || GENERAL_ADMINISTRATOR == userRoleNum) {
                CookieUtil.addCookie(response, String.valueOf(userRoleNum));
                return ReturnMessage.getReturnMessage(-1, "你没有系统的使用权限，有问题欢迎联系：jishupeixun@qunar.com", administratorModel);
            } else {
                return ReturnMessage.getReturnMessage(-1, "你没有系统的使用权限，有问题欢迎联系：jishupeixun@qunar.com", administratorModel);
            }
        } else {
            //登录失败，token值非法或者过期了，（token只能verify一次，验证后就失效了）
            log.info("login:用户不存在,token=" + token);
            return ReturnMessage.getReturnMessage(-1, "你没有系统的使用权限，有问题欢迎联系：jishupeixun@qunar.com", null);
        }

        //log.info("获取到的token为{}", token);
        //return ReturnMessage.getReturnMessage(-1, "你没有系统的使用权限，有问题欢迎联系：jishupeixun@qunar.com", null);
    }*/

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getParameter("token");
        log.info("controller里的token值为{}", token);
        QUser user = QSSO.getQUser(token);
        AdministratorModel administratorModel;
        log.info("user的名称：{}", user);
        if (null != user) {
            String userId = user.getUserId();
            administratorModel = administratorService.selectAdministratorByRtxId(userId);
            int userRoleNum = administratorModel.getRole();
            if (userRoleNum < 3) {
                log.info("cookie信息添加");
                CookieUtil.addCookie(response, userId);
                //userId = "123456";
                //log.info("userId的内容是：{}", userId);
                //String userId1 = AESUtil.encrypt(userId);
                //log.info("userId的内容是：{}", userId1);
                //Cookie cookie = new Cookie("userName", userId);
                //cookie.setMaxAge(60 * 60);
                //cookie.setDomain(".qunar.com");
                //cookie.setPath("/");
                //response.addCookie(cookie);
            } else {
                return "warn";
            }
        } else {
            // 登录失败，token值非法或者过期了，（token只能verify一次，验证后就失效了）
            log.info("login:用户不存在,token=" + token);
            return "index";
        }
        return "pointDetail";
    }


    @RequestMapping(value = "/noPrivilege")
    public ReturnMessage noPrivilege() {
        return ReturnMessage.getReturnMessage(-1, "你没有系统的使用权限，有问题欢迎联系：jishupeixun@qunar.com", null);
    }
}
