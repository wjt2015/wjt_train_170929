/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.servlet;

import javaweb.ctx.SingleApplicationContext;
import javaweb.dao.EmployeeListOndutyDao;
import javaweb.model.EmployeeListOnduty;
import javaweb.utils.BridgeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * 单例模式创建管理ApplicationContext
 * @author jintao.wang Date: 17-9-15 Time: 下午3:06
 */
@Slf4j
public class OndutyServlet extends HttpServlet {

    private ApplicationContext ctx;

    public void init() {
        System.out.println("OndutyServlet.init();start:");
        System.out.println("employeeListOndutyDao=" + BridgeUtil.getEmployeeListOndutyDao());
        System.out.println("OndutyServlet.init();finish!!");

        /* ctx = new ClassPathXmlApplicationContext("spring_context.xml"); */

        ctx = SingleApplicationContext.getInstance();

        System.out.println("\tctx=" + ctx);
        /* ApplicationContext ctx = new ClassPathXmlApplicationContext(); */

    }

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        System.out.println("OndutyServlet--void doGet() start:");
        getBodyData(httpServletRequest, httpServletResponse);

        EmployeeListOndutyDao employeeListOndutyDao = ctx.getBean(EmployeeListOndutyDao.class);

        EmployeeListOnduty employeeListOnduty = employeeListOndutyDao.selectEmployeeListOndutyById(3);
        System.out.println("employeeListOnduty=" + employeeListOnduty);
        String htmlStr = "<html><body>" + "<h2>Hello World!</h2><br/><h1>ondutyModule:"
                + employeeListOnduty.getOndutyModule() + "</h1><br/></body></html>";
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.write(htmlStr);

        System.out.println("\tOndutyServlet.doGet() finish!!");
    }

    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        System.out.println("doPost()");
        doGet(httpServletRequest, httpServletResponse);
    }

    public void destroy() {

    }

    private void getBodyData(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println("getBodyData() start!!");

        ServletContext servletContext = null;


        HttpSession httpSession = httpServletRequest.getSession(false);
        String sessionId = httpServletRequest.getRequestedSessionId();

        String queryString = httpServletRequest.getQueryString();
        String contextPath = httpServletRequest.getContextPath();
        String methodStr = httpServletRequest.getMethod();

        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("cookie=" + cookie);
            }
        }

        Enumeration<String> enumeration = httpServletRequest.getAttributeNames();
        String str = null;
        while (enumeration.hasMoreElements()) {
            str = enumeration.nextElement();
            System.out.println("str = " + str);
        }

        int contentLength = httpServletRequest.getContentLength();
        String contentType = httpServletRequest.getContentType();
        String authType = httpServletRequest.getAuthType();
        /*
         * BufferedReader reader = null; try { reader = httpServletRequest.getReader(); while((str = reader.readLine())
         * != null){ System.out.println("str = " + str); } } catch (IOException e) { e.printStackTrace(); }
         */

        try {
            byte[] buff = new byte[1024];
            int n = 0;
            ServletInputStream servletInputStream = httpServletRequest.getInputStream();
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

            while ((n = servletInputStream.read(buff)) >= 1) {
                servletOutputStream.write(buff);
            }
            System.out.println("对httpServletRequest和httpServletResponse读写数据结束!!");

        } catch (IOException e) {
            log.error("对httpServletRequest和httpServletResponse读写数据时失败", e);
        }

        Enumeration<String> headerEnumeration = httpServletRequest.getHeaderNames();
        String headerName = null, headerValue = null;
        while (headerEnumeration.hasMoreElements()) {
            headerName = headerEnumeration.nextElement();
            headerValue = httpServletRequest.getHeader(headerName);
            System.out.println("name=" + headerName + ";value=" + headerValue);
        }

        System.out.println("getBodyData() finish!!");
    }
}
