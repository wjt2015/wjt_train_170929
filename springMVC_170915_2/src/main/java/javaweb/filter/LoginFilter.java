/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.filter;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.qunar.ucenter.model.api.LoginUser;
import javaweb.model.LoginUserModel;
import javaweb.service.LoginUserService;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.joda.time.DateTime;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author jintao.wang Date: 17-9-30 Time: 下午9:32
 */
@Slf4j
public class LoginFilter implements Filter {

    private static final String DOMAIN = "Domain";

    private static final String FORWARD_URL = "FORWARD";

    private static final String ONDUTY_MAIN_PAGE = "pages/onduty_main.html";

    private LoginUserService loginUserService;

    private List<String> domainList;

    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext sc = filterConfig.getServletContext();
        WebApplicationContext webApplicationContext = (WebApplicationContext) sc
                .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        loginUserService = webApplicationContext.getBean(LoginUserService.class);

        /* 解析域名 */
        String domainStr = sc.getInitParameter(DOMAIN);
        if (domainStr != null) {
            domainList = Splitter.on(';').trimResults().omitEmptyStrings().splitToList(domainStr);
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = httpServletRequest.getReader();
        String strLine = null;
        /* 读取报文实体内的字符流 */
        while ((strLine = bufferedReader.readLine()) != null) {
            sb.append(strLine);
        }
        String userInfo = sb.toString();
        /* 利用报文实体内的字符流解析出用户名和密码 */
        List<String> stringList = Splitter.on('&').trimResults().omitEmptyStrings().splitToList(userInfo);
        String userName = Splitter.on('=').trimResults().omitEmptyStrings().splitToList(stringList.get(0)).get(1);
        String password = Splitter.on('=').trimResults().omitEmptyStrings().splitToList(stringList.get(1)).get(1);

        Byte newLogState = new Byte((byte) 1);

        LoginUserModel loginUserModel = new LoginUserModel(null, userName, password, new DateTime().getMillis(), null,
                newLogState);

        int iret = loginUserService.updateLoginUserModelByUserNameAndPassword(loginUserModel);

        if (iret == LoginUserService.NO_USER) {
            httpServletResponse.sendRedirect("pages/noregister.html");
            return;
        }

        if (iret >= 1) {
            /* 种植cookie */

            loginUserModel = loginUserService.selectLoginUserModelByNameAndPassword(userName,password);
            addCookieUponLogin(httpServletResponse,loginUserModel);

            String forwardUrl = getForwardUrl(httpServletRequest);
            if (forwardUrl != null) {
                httpServletResponse.sendRedirect(forwardUrl);
            } else {
                httpServletResponse.sendRedirect(ONDUTY_MAIN_PAGE);
            }
        }

    }

    public void destroy() {

    }

    /* 从请求数据中获取用户要达到的目标url */
    private String getForwardUrl(HttpServletRequest httpServletRequest) {
        String forwardUrl = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(FORWARD_URL)) {
                forwardUrl = cookie.getValue();
            }
        }
        return forwardUrl;
    }

    /**
     * 利用用户名、密码、登录时间戳构造MD5字符串
     * @param userName
     * @param password
     * @param loginTime
     * @return
     */
    private String getMD5String(String userName,String password,long loginTime){
        Preconditions.checkArgument(Strings.isNullOrEmpty(userName));
        Preconditions.checkArgument(Strings.isNullOrEmpty(password));

        StringBuilder sb = new StringBuilder();
        sb.append(userName);
        sb.append(password);
        sb.append(loginTime);
        byte[] srcData = sb.toString().getBytes();

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException",e);
        }
        byte[] destData = messageDigest.digest(srcData);

        return new String(destData);
    }

    /**
     *成功登录后为客户端种植cookie
     * @param httpServletResponse
     * @param loginUserModel
     * @return
     */
    private int addCookieUponLogin(HttpServletResponse httpServletResponse,LoginUserModel loginUserModel){
        Preconditions.checkNotNull(httpServletResponse);
        Preconditions.checkNotNull(loginUserModel);

        /*记录种植的cookie个数*/
        int nCookie = 0;
        if(domainList != null && domainList.size() >= 1){
            Integer id = loginUserModel.getId();
            String userName = loginUserModel.getUserName();
            String password = loginUserModel.getPassword();
            Long loginTime = loginUserModel.getLoginTime();

            for (String domain:domainList){
                Cookie cookie = new Cookie("ID",id.toString());
                cookie.setDomain(domain);
                httpServletResponse.addCookie(cookie);

                String md5 = getMD5String(userName,password,loginTime);
                cookie = new Cookie("USERINFO",md5);
                cookie.setDomain(domain);
                httpServletResponse.addCookie(cookie);

                nCookie += 2;
            }
        }
        return nCookie;
    }

}



