/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.servlet;

import javaweb.ctx.SingleApplicationContext;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 单例模式创建管理ApplicationContext
 *
 * @author jintao.wang Date: 17-9-15 Time: 上午10:09
 */
public class MyServlet extends HttpServlet {

    private String msg;

    private ApplicationContext ctx;

    public void init() throws ServletException {
        msg = "Hello world,wjt!!";
/*        ctx = SingleApplicationContext.getInstance();*/
        System.out.println("MyServlet.init()");
    }

    /* 该servlet只负责初始化spring容器 */
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

          Cookie[] cookies = httpServletRequest.getCookies(); for (Cookie cookie : cookies) {
          System.out.println("cookie=" + cookie); } httpServletResponse.setContentType("text/html"); PrintWriter
          printWriter = httpServletResponse.getWriter(); printWriter.write("<h1>" + msg + "</h1>");
/*        throw new ServletException("");*/
    }

    public void destroy() {
        System.out.println("MyServlet.destroy()");
    }
}
