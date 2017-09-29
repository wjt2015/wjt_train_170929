/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.servlet;

import javaweb.ctx.SingleApplicationContext;
import javaweb.dbconn.MySQLSingleConn;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Map;

/**
 * 专门处理图片的上传和下载
 * 
 * @author jintao.wang Date: 17-9-19 Time: 上午11:29
 */
@Slf4j
public class PicHttpServlet extends HttpServlet {

    private ApplicationContext ctx;

    private static int id = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("\tPicHttpServlet--doGet() start!!");
        resp.setCharacterEncoding("UTF-8");

        /*
         * try { getPicFromServer(req,resp); } catch (SQLException e) { log.error("SQLException!!",e); }
         */
         formData(req, resp);
/*        setHeader(req, resp);*/
/*        testCookie(req, resp);*/

        System.out.println("\tPicHttpServlet--doGet() finish!!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("\tPicHttpServlet--doPost() start!!");
        resp.setCharacterEncoding("UTF-8");

        /*uploadPic(req);*/

        formData(req, resp);

        System.out.println("\tPicHttpServlet--doPost() finish!!");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        ctx = SingleApplicationContext.getInstance();
    }

    private void getPicFromServer(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String idStr = req.getHeader("picId");
        /* idStr = "3"; */

        if (idStr == null) {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter printWriter = resp.getWriter();
            printWriter.println("未指定图片文件id，无法返回图片！！");
        } else {
            int id = Integer.parseInt(idStr);

            ServletOutputStream servletOutputStream = resp.getOutputStream();
            Connection connection = MySQLSingleConn.getInstance();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT picObj FROM pic_table WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            InputStream inputStream = rs.getBinaryStream("picObj");

            /* resp.setContentType("application/x-png"); */

            byte[] buff = new byte[1024];
            int n = 0;
            while ((n = inputStream.read(buff)) >= 1) {
                servletOutputStream.write(buff);
            }
        }

        System.out.println("void getPicFromServer()  finish!!");
    }

    private void uploadPic(HttpServletRequest httpServletRequest) {
        String fileName = httpServletRequest.getHeader("picName");
        if (fileName == null) {
            fileName = "pic.png";
        }
        int contentLength = httpServletRequest.getContentLength();
        try {
            ServletInputStream servletInputStream = httpServletRequest.getInputStream();
            Connection connection = MySQLSingleConn.getInstance();
            connection.setAutoCommit(true);
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO pic_table(name,picObj) VALUES (?,?)");
            preparedStatement.setString(1, fileName);
            preparedStatement.setBinaryStream(2, servletInputStream, contentLength);
            preparedStatement.executeUpdate();
        } catch (IOException e) {
            log.error("IOException!!", e);
        } catch (SQLException e) {
            log.error("SQLException!!", e);
        }
        System.out.println("uploadPic() finish!!");
    }

    private void localhostDownload(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        byte[] buff = new byte[1024];
        int n = 0;
        /* 读取本机图片数据并写入resp */
        File file = new File("/home/linux2014/WJT_2017/projects/CXX_Projects/pic1.jpg");
        FileImageInputStream fileImageInputStream = new FileImageInputStream(file);
        while ((n = fileImageInputStream.read(buff)) >= 1) {
            servletOutputStream.write(buff);
        }
    }

    private void localhostUpload(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletInputStream servletInputStream = req.getInputStream();
        File file = new File("/home/linux2014/WJT_2017/projects/CXX_Projects/upload_pic" + id + ".jpg");
        FileImageOutputStream fileImageOutputStream = new FileImageOutputStream(file);
        byte[] buff = new byte[1024];
        int n = 0;
        while ((n = servletInputStream.read(buff)) >= 1) {
            fileImageOutputStream.write(buff);
        }
    }

    private void formData(HttpServletRequest req, HttpServletResponse resp) {
        String queryString = req.getQueryString();
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String contentType = req.getContentType();
        StringBuffer url = req.getRequestURL();
        String remoteUser = req.getRemoteUser();
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.println("key=" + entry.getKey() + ";value=" + entry.getValue());
        }

        Enumeration<String> enumerationHeader = req.getHeaderNames();

        while (enumerationHeader.hasMoreElements()){
            String str = enumerationHeader.nextElement();
            String value = req.getHeader(str);
            System.out.println("name:" + str + ";value:" +  value);
        }

        BufferedReader reader = null;
        try {
            reader = req.getReader();
            String strLine = null;
            while ((strLine = reader.readLine()) != null){
                System.out.println("strLine:" + strLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("formData() finish!!");
    }

    private void setHeader(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setIntHeader("Refresh", 5);
        resp.setContentType("text/html");
        DateTime dt = new DateTime();
        String dtStr = dt.toString("yyyy-MM-dd HH:mm:ss");

        // 格式化输出
        PrintWriter out = resp.getWriter();
        String title = "自动刷新 Header 设置 - 菜鸟教程实例";
        String docType = "<!DOCTYPE html>\n";
        out.println(
                docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n"
                        + "<h1 align=\"center\">" + title + "</h1>\n" + "<p>当前时间是：" + dtStr + "</p>\n");

        System.out.println("void setHeader() finish!!");
    }

    /*cookie*/
    private void testCookie(HttpServletRequest req, HttpServletResponse resp){
        Cookie[] cookies = req.getCookies();

        if (cookies != null){
            for (Cookie cookie:cookies){
                String name = cookie.getName();
                String value = cookie.getValue();
                String comment = cookie.getComment();
                String domain = cookie.getDomain();
                String path = cookie.getPath();
                int version = cookie.getVersion();
                boolean bSecure = cookie.getSecure();
                int maxAge = cookie.getMaxAge();
                System.out.println("name:" + name + ";value:" + value + ";comment:" + comment + ";domain:" + domain + "path:" + path + ";version:" + version +";bSecure:" + bSecure + ";maxAge:" + maxAge);
                cookie.setMaxAge(1000);
            }
        }

        /*写cookie*/
        Cookie cookie = new Cookie("OS","ubuntu");
        cookie.setMaxAge(1000);
        resp.addCookie(cookie);
        System.out.println("void testCookie() finish!!");
    }
}
