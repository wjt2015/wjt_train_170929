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
import javax.servlet.http.Cookie;
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
/**
 如果用户未被授权，则返回一个登录页；
 若已被授权，则放行；
 如果带有登录信息，则访问数据库，检查其合理性，若不合理，返回登录页面，否则，造四个cookie，分别代表用户名、密码、角色、sessionID；
 */
public class AuthFilter implements Filter {

    private static final String NAME = "userNamr";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String SESSIONID = "sessionID";

    private ApplicationContext ctx;

    private AuthService authService;

    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        ctx = (ApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        authService = ctx.getBean(AuthServiceImpl.class);
        System.out.println("init() finish!!");
/*        ctx = SingleApplicationContext.getInstance();*/
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;


/*        String[] userInfoArr = getRequestInfo(httpServletRequest,httpServletResponse);

        if(userInfoArr.length == 3){
            String userName = userInfoArr[0];
            String password = userInfoArr[1];
            Byte role = Byte.parseByte(userInfoArr[2]);
            AuthModel authModel = new AuthModel(null,userName,password,role);
            AuthService authService = ctx.getBean(AuthService.class);
            authService.insertAuthModel(authModel);
        }*/

        HttpSession httpSession = httpServletRequest.getSession(false);
        /*没有会话，说明尚未登录，需要检查登录信息*/
        if (httpSession == null){

        }
        else {

        }
    }

    public void destroy() {
        ctx = null;
        authService = null;
    }

    private String[] getRequestInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException{

        String remoteUser = httpServletRequest.getRemoteUser();
        StringBuffer requestUrl = httpServletRequest.getRequestURL();
        String requestURI = httpServletRequest.getRequestURI();

        String requestedSessionId = httpServletRequest.getRequestedSessionId();


        String sessionId = null;
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession != null){
            sessionId = httpSession.getId();
            System.out.println("\tsessionId:" + sessionId);
        }

        String strLine = null;
        BufferedReader reader = httpServletRequest.getReader();
        List<String> stringList = new LinkedList<String>();
        while ((strLine = reader.readLine()) != null){
            stringList.addAll(Splitter.on('&').omitEmptyStrings().trimResults().splitToList(strLine));
        }

        List<String> userInfoList = new LinkedList<String>();

        if (stringList != null && stringList.size() == 3){
            for (String str:stringList){
                List<String> strList = Splitter.on('=').omitEmptyStrings().trimResults().splitToList(str);
                if (strList.size() == 2){
                    userInfoList.add(strList.get(1));
                }
            }
        }

        System.out.println("\tgetRequestInfo() finish!!");

        return userInfoList.toArray(new String[]{});
    }

    /**
     * 检验用户是否被授权
     */
    private boolean checkAuthorized(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String userName = null;
        String password = null;
        Byte role = null;
        Integer id = null;

        HttpSession httpSession = httpServletRequest.getSession();
        String cookieSessionId = null, sessionId = null;
        if (httpSession != null){
            sessionId = httpSession.getId();
        }

        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie:cookies){
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();

            if(cookieName.equals(NAME)){
                userName = cookieValue;
            }
            else if(cookieName.equals(PASSWORD)){
                password = cookieValue;
            }
            else if(cookieName.equals(ROLE)){
                role = Byte.parseByte(cookieValue);
            }
            else if(cookieName.equals(SESSIONID)){
                cookieSessionId = cookie.getValue();
                if (!sessionId.equals(cookieSessionId)){
                    return false;
                }
            }

        }


        AuthModel authModel = new AuthModel(null,userName,password,role);
        List<Integer> idList = authService.selectAuthModelByNamePasswordRole(authModel);


        return (idList != null && idList.size() >= 1);
    }

}



