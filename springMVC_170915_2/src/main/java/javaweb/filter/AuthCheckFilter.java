/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.filter;

import com.alibaba.fastjson.JSON;
import javaweb.model.LoginUserModel;
import javaweb.service.LoginUserService;
import javaweb.util.MD5String;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author jintao.wang Date: 17-10-6 Time: 下午8:44
 */
public class AuthCheckFilter implements Filter {

    private static final String ID = "ID";
    private static final String USER_INFO = "USER_INFO";

    private static final String FORWARD_URL = "FORWARD";

    private LoginUserService loginUserService;

    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext sc = filterConfig.getServletContext();
        WebApplicationContext webApplicationContext = (WebApplicationContext) sc
                .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        loginUserService = webApplicationContext.getBean(LoginUserService.class);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (checkIsAuthenticated((httpServletRequest))) {
            /* 检验通过,继续后边的处理 */
            /*chain.doFilter(httpServletRequest, httpServletResponse);*/

            String uri = httpServletRequest.getRequestURI();
            uri += "l";
            String viewName = uri;
            ModelAndView mv = new ModelAndView(viewName);
            String jsonString = JSON.toJSONString(mv);
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.write(jsonString);
        } else {
            /* 检验不通过,存储该用户刚才请求的url,向前端报错 */
            StringBuffer sb = httpServletRequest.getRequestURL();
            String queryString = httpServletRequest.getQueryString();
            sb.append("?");
            sb.append(queryString);
            String forwardUrl = sb.substring(0);
            /* 将登录通过后要请求的url存入session */
            HttpSession httpSession = httpServletRequest.getSession(true);
            httpSession.setAttribute(FORWARD_URL, forwardUrl);
        }

    }

    public void destroy() {
    }

    private boolean checkIsAuthenticated(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        Integer id = null;
        String md5String = null;
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            if (name.equals(ID)) {
                value = cookie.getValue();
                id = Integer.parseInt(value);
            } else if (name.equals(USER_INFO)) {
                md5String = value;
            }
        }
        if (id == null || md5String == null) {
            return false;
        }

        LoginUserModel loginUserModel = loginUserService.selectLoginUserModelById(id);
        if (loginUserModel == null) {
            return false;
        }
        if (loginUserModel.getIsLogin() == 0){
            return false;
        }
        String md5StringInDB = MD5String.getMD5String(loginUserModel.getUserName(), loginUserModel.getPassword(),
                loginUserModel.getLoginTime());
        if (md5String.equals(md5StringInDB)) {
            return true;
        } else {
            return false;
        }
    }
}
