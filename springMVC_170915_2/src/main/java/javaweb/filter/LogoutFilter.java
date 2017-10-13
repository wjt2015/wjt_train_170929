/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.filter;

import com.alibaba.fastjson.JSON;
import javaweb.model.CookieNameValue;
import javaweb.model.CookieRecord;
import javaweb.model.InitParameter;
import javaweb.model.LoginUserModel;
import javaweb.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author jintao.wang  Date: 17-10-6 Time: 下午8:46
 */
public class LogoutFilter implements Filter {

    /*用于存储web.xml文件内配置的参数，如域名列表*/

    private InitParameter initParameter;

    private static final String ID = "ID";
    private static final String USER_INFO = "USER_INFO";

    private LoginUserService loginUserService;

    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext sc = filterConfig.getServletContext();
        WebApplicationContext webApplicationContext = (WebApplicationContext)sc.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        loginUserService = webApplicationContext.getBean(LoginUserService.class);
        initParameter = webApplicationContext.getBean(InitParameter.class);
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        logout(httpServletRequest,httpServletResponse);
    }

    public void destroy() {

    }

    /**
     * 注销时，需要通知前端删除相关的cookie，并修改数据库内的登录状态
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws IOException
     */
    private void logout(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
            throws IOException {
        CookieRecord cookieRecord = new CookieRecord();
        Cookie[] cookies = httpServletRequest.getCookies();
        Integer id = null;
        String name = null,value = null,domain = null;
        for (Cookie cookie:cookies){
            name = cookie.getName();
            value = cookie.getValue();
            if (name.equals(ID)){
                id = Integer.parseInt(value);

                CookieNameValue cookieNameValue = new CookieNameValue(name,value);
                cookieRecord.addCookieNameValue(cookieNameValue);
            }else if (name.equals(USER_INFO)){
                CookieNameValue cookieNameValue = new CookieNameValue(name,value);
                cookieRecord.addCookieNameValue(cookieNameValue);
            }
            domain = cookie.getDomain();
            cookieRecord.addDomain(domain);
        }
        String cookieRecordJson = JSON.toJSONString(cookieRecord);
        PrintWriter printWriter = httpServletResponse.getWriter();
        /*将需要删除的cookie标识发给前端，前端据此删除相应的cookie*/
        printWriter.write(cookieRecordJson);
        /*修改数据库内该用户的登录状态*/
        if (id != null){
            LoginUserModel loginUserModel = new LoginUserModel();
            loginUserModel.setId(id);
            loginUserModel.setIsLogin(new Byte((byte)0));
            loginUserService.updateLoginUserModelById(loginUserModel);
        }
    }
}
    