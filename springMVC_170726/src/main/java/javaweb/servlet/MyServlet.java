/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author jintao.wang  Date: 17-9-15 Time: 上午10:09
 */
public class MyServlet extends HttpServlet {

    private String msg;

    public void init() throws ServletException {
        msg = "Hello world";
        System.out.println("MyServlet.init()");
    }

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        httpServletResponse.setContentType("text/html");

        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.write("<h1>" + msg + "</h1>");
    }

    public void destroy() {
        System.out.println("MyServlet.destroy()");
    }
}



