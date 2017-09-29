/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author jintao.wang Date: 17-9-22 Time: 下午9:23
 */
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet() start:");
        readFile(req, resp);
        System.out.println("doGet() finish!!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost() start:");
        readFile(req, resp);
        System.out.println("doPost() finish!!");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    /*解析上传的文件并将文件内容显示给前端*/
    private void readFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        File file = new File("/home/linux2014/WJT_2017/projects/CXX_Projects/upload");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));

        ServletInputStream servletInputStream = req.getInputStream();
        byte[] buff = new byte[1024];
        int n = 0;
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        while ((n = servletInputStream.read(buff)) >= 1){
            servletOutputStream.write(buff,0,n);
            bufferedOutputStream.write(buff,0,n);
        }
        if(bufferedOutputStream != null){
            bufferedOutputStream.close();
        }
        resp.setContentType("text/html;charset=UTF-8");
        resp.sendRedirect("http://localhost:8080/springMVC_170915_2_tomcat/a.t");
        System.out.println("After sendRedirect()");
    }
}
