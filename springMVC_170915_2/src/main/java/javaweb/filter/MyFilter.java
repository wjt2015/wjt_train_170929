/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.filter;

import com.google.common.base.Objects;
import javaweb.verification.VerificationCode;
import org.joda.time.DateTime;

import javax.imageio.ImageIO;
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
import java.util.Enumeration;

/**
 * @author jintao.wang Date: 17-9-19 Time: 下午8:45
 */
public class MyFilter implements Filter {

    private VerificationCode verificationCode;

    public void init(FilterConfig filterConfig) throws ServletException {

        verificationCode = new VerificationCode();

        String filterName = filterConfig.getFilterName();
        Enumeration<String> stringEnumeration = filterConfig.getInitParameterNames();
        String str = null, value = null;
        while (stringEnumeration.hasMoreElements()) {
            str = stringEnumeration.nextElement();
            value = filterConfig.getInitParameter(str);
            System.out.println("initParam=" + str + ";value=" + value);
        }
        ServletContext servletContext = filterConfig.getServletContext();
        System.out.println("servletContext=" + servletContext);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("void doFilter() start:");
        loginCheck(request, response, chain);
        /* chain.doFilter(request,response); */
        System.out.println("void doFilter() finish!!");
    }

    public void destroy() {

    }

    private void loginCheck(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession == null) {
            httpSession = httpServletRequest.getSession(true);
            httpSession.setAttribute("sessionID", new DateTime());
            httpSession.setMaxInactiveInterval(100);
        }

        boolean bNew = httpSession.isNew();
        ServletContext servletContext = httpSession.getServletContext();
        String httpSessionId = httpSession.getId();
        long createTime = httpSession.getCreationTime();
        DateTime dt = new DateTime(createTime);
        String formatDateTime = dt.toString("yyyy-MM-dd HH-mm-ss");
        System.out.println("\tdt=" + formatDateTime);
        Enumeration<String> stringEnumeration = httpSession.getAttributeNames();
        while (stringEnumeration.hasMoreElements()) {
            String name = stringEnumeration.nextElement();
            Object value = httpSession.getAttribute(name);
            System.out.println("\tname:" + name + ";value:" + value);
        }


        /*读取req的报文数据*/
        BufferedReader reader = httpServletRequest.getReader();
        String strLine = null;
        while((strLine = reader.readLine()) != null){
            System.out.println("strLine=" + strLine);
        }

        httpServletResponse.setIntHeader("Expires", 50);
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setHeader("Pragma", "-1");

        httpSession.setAttribute("verificationCode", verificationCode.getVerificationCode());
        ImageIO.write(verificationCode.getVerificationImage(), "png", httpServletResponse.getOutputStream());

        System.out.println("httpSession = " + httpSession);
    }
}
