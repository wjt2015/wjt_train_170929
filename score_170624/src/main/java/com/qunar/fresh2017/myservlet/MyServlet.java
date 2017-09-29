package com.qunar.fresh2017.myservlet;

import com.qunar.fresh2017.model.AdministratorModel;
import com.qunar.fresh2017.service.AdministratorService;
import com.qunar.fresh2017.service.impl.AdministratorServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * Created by linux2014 on 17-7-7.
 */
@Slf4j
public class MyServlet extends HttpServlet {
    private ServletContext context;
    private ServletConfig config;
    private AdministratorService administratorService;

    private static final String prefixPath = "/home/linux2014/WJT_2017/projects/my_res/score_tomcat/download";

    public MyServlet(){
//        administratorService = new AdministratorServiceImpl();
        log.info("MyServlet starts to be created!");
    }
    @Override
    public void init(ServletConfig config){
        this.config = config;
        context = config.getServletContext();
        log.info("\tMyServlet.init()\n");
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        outputChineseByOutputStream(req,resp);
//        returnPic(req,resp);
//        getClientInfo(req,resp);
//        getClientInfoHeader(req,resp);
        returnRequestPic(req,resp);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        printWriter.println("<HTML>");
        printWriter.println("<HEAD><TITLE>A Servlet</TITLE></HEAD>");
        printWriter.println("<BODY>");
        printWriter.println("This is");
        printWriter.println(getClass());
        printWriter.println(",using the POST method");

        printWriter.println("</BODY>");
        printWriter.println("</HTML>");
        printWriter.flush();
        printWriter.close();
        /****/
        Cookie cookie = new Cookie("cookie1_k","cookie1_v");
        resp.addCookie(cookie);
        cookie = new Cookie("cookie2_k","cookie2_v");
        resp.addCookie(cookie);
    }
    /*按需求返回图片*/
    private void returnRequestPic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String picName = req.getRequestURI();
        int index = picName.lastIndexOf('/');
        if(index >= 0){
            picName = picName.substring(index + 1);
        }
        index = picName.lastIndexOf('.');
        String type = picName.substring(index + 1);
        resp.setContentType(type);
        String path = prefixPath + "/" + picName;
        File picFile = new File(path);
        BufferedImage bufferedImage = ImageIO.read(picFile);
        OutputStream outputStream = resp.getOutputStream();
        ImageIO.write(bufferedImage,type,outputStream);
        log.info("bufferedImage:" + bufferedImage);
    }
    /*返回图片*/
    private void returnCopyPIC(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("image/png");
        String fileName = "http_resp.png";
        String path = prefixPath + "/" + fileName;
        File fileForPic = new File(path);
        BufferedImage bufferedImage = ImageIO.read(fileForPic);
        OutputStream outputStream = resp.getOutputStream();
        boolean bret = ImageIO.write(bufferedImage,"png",outputStream);
        log.info("\tbufferedImage:" + bufferedImage);
    }
    /*获取客户端头部信息*/
    private void getClientInfoHeader(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("获取到的客户端的所有请求头信息如下：");
        printWriter.write("<hr/>");
        Enumeration<String> reqHeadInfos = req.getHeaderNames();
        while (reqHeadInfos.hasMoreElements()){
            String headName = (String)reqHeadInfos.nextElement();
            String headValue = req.getHeader(headName);
            printWriter.write(headName + "--" + headValue);
            printWriter.write("<br/>");
        }
        printWriter.write("<br/>" + "" + "获取到的客户端Accept-Encoding请求头信息的值:" + "<hr/>");
        String value = req.getHeader("Accept-Encoding");
        printWriter.write(value + "<br/>");
        Enumeration<String> es = req.getHeaders("Accept-Encoding");
        printWriter.write("req.getHeaders(\"Accept-Encoding\")<br/><hr>");
        while (es.hasMoreElements()){
            String str = (String)es.nextElement();
            printWriter.write(str + ";");
        }
        log.info("++++++++++++++=");
    }
    private void readFile(PrintWriter printWriter,String fname) throws IOException {
        int i = 0;
        InputStream in = context.getResourceAsStream(fname);
        Properties properties = new Properties();
        properties.load(in);
        String jdbcDriver = properties.getProperty("jdbcDriver");
        String jdbc_url = properties.getProperty("jdbc_url");
        String jdbc_username = properties.getProperty("jdbc_username");
        String jdbc_password = properties.getProperty("jdbc_password");
        String[] propNameArr = {"jdbc_url","jdbc_url","jdbc_username","jdbc_password"};
        String[] propValueArr = new String[propNameArr.length];
        for (i = 0;i < propNameArr.length;i++){
            propValueArr[i] = properties.getProperty(propNameArr[i]);
        }
        in.close();
        printWriter.println("<hr>");
        for(i = 0;i < propNameArr.length;i++){
            printWriter.println("<br>" + propNameArr[i] + ":" +propValueArr[i] + "<br/>");
        }
        log.info("MyServlet.readFile()");
    }
    private void getClientInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestUrlStr = req.getRequestURL().toString();
        String requestUri = req.getRequestURI();
        String queryStr = req.getQueryString();
        String remoteAddr = req.getRemoteAddr();
        String remoteHost = req.getRemoteHost();
        int remotePort = req.getRemotePort();
        String remoteUser = req.getRemoteUser();
        String method = req.getMethod();
        String pathInfo = req.getPathInfo();
        String localAddr = req.getLocalAddr();
        String localName = req.getLocalName();
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Type","text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("获取到的客户端信息如下：");
        printWriter.write("<hr/>");
        printWriter.write("请求的URL地址：" + requestUrlStr);
        printWriter.write("<br/>");
        printWriter.write("请求的资源：" + requestUri + "<br/>");
        printWriter.write("请求的URL地址中附带的参数：" + queryStr + "<br/>");
        printWriter.write("来访者的IP地址：" + remoteAddr + "<br/>");
        printWriter.write("来访者的主机名：" + remoteHost + "<br/>");
        printWriter.write("使用的端口号：" + remotePort + "<br/>");
        printWriter.write("remoteUser:" + remoteUser + "<br/>");
        printWriter.write("请求使用的方法：" + method + "<br/>");
        printWriter.write("pathInfo:" + pathInfo + "<br/>");
        printWriter.write("localAddr:" + localAddr + "<br/>");
        printWriter.write("localName:" + localName);
    }
    /*生成随机图片，作为验证码*/
    /*利用HttpServletResponse给客户端返回图片*/
    private void returnPic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedImage image = new BufferedImage(80,20,BufferedImage.TYPE_INT_BGR);
        Graphics2D g = (Graphics2D)image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,80,20);

        g.setColor(Color.BLUE);
        g.setFont(new Font(null,Font.BOLD,20));
        g.drawString(makeRandNum(),0,20);
        /*每隔5秒刷新一次*/
        resp.setHeader("refresh","5");
        /*设置客户端的打开方式*/
        resp.setHeader("Content-Type","image/jpeg");
//        resp.setContentType("image/jpeg");
        /*告知浏览器不缓存*/
        resp.setDateHeader("expires",-1);
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pragma","no-cache");
        ImageIO.write(image,"jpg",resp.getOutputStream());
        log.info("image={}" + image);
    }
    private String makeRandNum(){
        String ret = new String();
        Random random = new Random();
        int i = 0,n = 6;
        Integer tv = null;
        for(i = 0;i < n;i++){
            tv = random.nextInt()%10;
            ret += tv.toString();
        }
        return ret;
    }
    /*利用OutputStream将中文字符串输出到客户端，需要设置服务端和客户端的编码方案是一致的*/
    private void outputChineseByOutputStream(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String str="中华人民共和国";
        /*通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码*/
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        byte[] byteArr = str.getBytes("utf8");
        OutputStream outputStream = resp.getOutputStream();
        outputStream.write(byteArr);
        log.info("byteArr={}" + byteArr);
    }
    /**
     * 返回网页
     */
    private void returnHTML(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        printWriter.println("<HTML>");
        printWriter.println("<HEAD><TITLE>A Servlet</TITLE></HEAD>");
        printWriter.println("<BODY>");
        printWriter.println("This is");
        printWriter.println(getClass());
        printWriter.println(",using the GET method");
        /****/
        Cookie cookie = new Cookie("cookie1_k","cookie1_v");
        resp.addCookie(cookie);
        cookie = new Cookie("cookie2_k","cookie2_v");
        resp.addCookie(cookie);
        /*ServletConfig*/
        printWriter.println("<hr>");
        Enumeration<String> names = config.getInitParameterNames();
        String name = null,value = null;
        while (names.hasMoreElements()){
            name = names.nextElement();
            value = config.getInitParameter(name);
            printWriter.println("<br>" + name + "=" + value + "<br/>");
        }

        context.setAttribute("context","你好");
        RequestDispatcher rd = context.getRequestDispatcher("/my.txt");
        rd.forward(req,resp);
        /*读取资源文件，并写入resp；资源文件的根目录是webapp*/
        String fname = "mysql.properties";
        readFile(printWriter,fname);



        printWriter.println("</BODY>");
        printWriter.println("</HTML>");
        printWriter.flush();
        printWriter.close();
        log.info("MyServlet.doGet()");
    }
}
