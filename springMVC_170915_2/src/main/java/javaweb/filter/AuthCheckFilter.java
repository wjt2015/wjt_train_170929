/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.filter;

import javaweb.service.LoginUserService;
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
import java.io.IOException;

/**
 * @author jintao.wang  Date: 17-10-6 Time: 下午8:44
 */
public class AuthCheckFilter implements Filter {

    private LoginUserService loginUserService;

    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext sc = filterConfig.getServletContext();
        WebApplicationContext webApplicationContext = (WebApplicationContext)sc.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        loginUserService = webApplicationContext.getBean(LoginUserService.class);
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

    }

    public void destroy() {

    }
}
    