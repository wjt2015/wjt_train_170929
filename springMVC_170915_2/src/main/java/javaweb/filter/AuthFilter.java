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
import lombok.extern.slf4j.Slf4j;
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
import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
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
@Slf4j
public class AuthFilter implements Filter {

    private static final String NAME = "userNamr";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String SESSIONID = "sessionID";

    private static final String GET = "GET";
    private static final String POST = "POST";

    private static final String QUNAR_DOMAIN = "qunar.com";
    private static final String QUNARMAN_DOMAIN = "qunarman.com";

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

        /*读取uri参数*/
        System.out.println("\t输出uri参数信息：");
        Enumeration<String> stringEnumeration = httpServletRequest.getParameterNames();
        while (stringEnumeration.hasMoreElements()){
            String name = stringEnumeration.nextElement();
            String value = httpServletRequest.getParameter(name);
            System.out.println("\tname:" + name + ";value:" + value);
        }

        System.out.println("\t输出头部信息：");
        stringEnumeration = httpServletRequest.getHeaderNames();
        while (stringEnumeration.hasMoreElements()){
            String name = stringEnumeration.nextElement();
            String value = httpServletRequest.getHeader(name);
            System.out.println("\tname:" + name + ";value:" + value);
        }

        String contentType = httpServletRequest.getContentType();
        String contentPath = httpServletRequest.getContextPath();
        String method = httpServletRequest.getMethod();
        String queryString = httpServletRequest.getQueryString();
        int contentLength = httpServletRequest.getContentLength();
        String protocol = httpServletRequest.getProtocol();
        String servletPath = httpServletRequest.getServletPath();
        String authType = httpServletRequest.getAuthType();
        String serverName = httpServletRequest.getServerName();
        int serverPort = httpServletRequest.getServerPort();

        String authString = getAuthString(httpServletRequest);
        System.out.println("\tgetRequestInfo() finish!!");

        return parseString(authString);
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
        else {
            return false;
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

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @return 若成功，则返回true；否则返回false;
     */
    private boolean setCookie(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        HttpSession httpSession = httpServletRequest.getSession(true);
        String sessionId = httpSession.getId();

        String authString = getAuthString(httpServletRequest);

        String[] authInfo = parseString(authString);
        if (authInfo.length != 3){
            return false;
        }
        String[] cookieName = {NAME,PASSWORD,ROLE};
        for (int i = 0;i < cookieName.length;i++){
            Cookie cookie = new Cookie(cookieName[i],authInfo[i]);
            cookie.setDomain(QUNAR_DOMAIN);
            httpServletResponse.addCookie(cookie);

            cookie = new Cookie(cookieName[i],authInfo[i]);
            cookie.setDomain(QUNARMAN_DOMAIN);
            httpServletResponse.addCookie(cookie);

        }
        Cookie cookie = new Cookie(NAME,authInfo[0]);
        cookie.setDomain("");
        return true;
    }



    /**
     * 提取包含用户认证信息的字符串
     * @param httpServletRequest
     * @return
     */
    private String getAuthString(HttpServletRequest httpServletRequest){
        String authString = null;
        if(httpServletRequest.getMethod().contentEquals(GET)){
            authString = httpServletRequest.getQueryString();
        }
        else  if (httpServletRequest.getMethod().contentEquals(POST)){
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = httpServletRequest.getReader();
                authString = bufferedReader.readLine();
            } catch (IOException e) {
                log.error("IOException happens!!",e);
            }
        }
        return authString;
    }
    /**
     * 解析出NAME、PASSWORD、ROLE；
     * @param string,not null
     * @return
     */
    private String[] parseString(String string){
        List<String> stringList = new LinkedList<String>();
        stringList.addAll(Splitter.on('&').omitEmptyStrings().trimResults().splitToList(string));

        List<String> userInfoList = new LinkedList<String>();


        if(stringList != null && stringList.size() == 3){
            for (String str:stringList){
                List<String> strList = Splitter.on('=').omitEmptyStrings().trimResults().splitToList(str);
                if(strList.size() == 2){
                    userInfoList.add(strList.get(1));
                }
            }
        }
        return userInfoList.toArray(new String[]{});
    }


}



