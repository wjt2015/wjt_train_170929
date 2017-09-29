/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.filter;

import com.google.common.base.Splitter;
import javaweb.ctx.SingleApplicationContext;
import javaweb.model.AuthModel;
import javaweb.service.AuthService;
import javaweb.service.impl.AuthServiceImpl;
import javaweb.util.MyConsumer;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jintao.wang  Date: 17-9-26 Time: 下午9:21
 */
public class AuthFilter implements Filter {

    private ApplicationContext ctx;

    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        ctx = (ApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        System.out.println("init() finish!!");
/*        ctx = SingleApplicationContext.getInstance();*/
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;


        String[] userInfoArr = getRequestInfo(request,response,chain);

        if(userInfoArr.length == 3){
            String userName = userInfoArr[0];
            String password = userInfoArr[1];
            Byte role = Byte.parseByte(userInfoArr[2]);
            AuthModel authModel = new AuthModel(null,userName,password,role);
            AuthService authService = ctx.getBean(AuthService.class);
            authService.insertAuthModel(authModel);
        }

        HttpSession httpSession = httpServletRequest.getSession(false);
        /*没有会话，说明尚未登录，需要检查登录信息*/
        if (httpSession == null){

        }
        else {

        }
    }

    public void destroy() {
        ctx = null;
    }

    private String[] getRequestInfo(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException{
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        String strLine = null;
        BufferedReader reader = httpServletRequest.getReader();
        List<String> stringList = new LinkedList<>();
        while ((strLine = reader.readLine()) != null){
            stringList.addAll(Splitter.on('&').omitEmptyStrings().trimResults().splitToList(strLine));
        }

        List<String> userInfoList = new LinkedList<>();

        if (stringList != null && stringList.size() == 3){
            for (String str:stringList){
                List<String> strList = Splitter.on('=').omitEmptyStrings().trimResults().splitToList(str);
                if (strList.size() == 2){
                    userInfoList.add(strList.get(1));
                }
            }
        }

        System.out.println("getRequestInfo() finish!!");

        return userInfoList.toArray(new String[]{});
    }

}



