package com.qunar.fresh2017.myservlet;

import com.qunar.fresh2017.model.AdministratorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by linux2014 on 17-7-7.
 */
@Slf4j
public class HttpServletForDownLoad extends HttpServlet {

    private static final String prefixPath = "/home/linux2014/WJT_2017/projects/my_res";

    private ServletConfig config;
    private ServletContext context;
    @Override
    public void init(ServletConfig config){
        this.config = config;
        context = config.getServletContext();
        log.info("HttpServletForDownLoad.init()");
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        downLoadFileByOutputStream(req,resp);
//        int i = 0;
//        List<AdministratorModel> administratorModelList = (List<AdministratorModel>)context.getAttribute("administratorModelList");
//        for(AdministratorModel administratorModel:administratorModelList){
//            String line = "<br>" + administratorModel.getId() + "," + administratorModel.getCn() + "," +
//                    administratorModel.getDep1() + "<br/>";
//            log.info("index=" + i +";---" + line);
//        }
        String value = (String) context.getAttribute("context");
        log.info("value=" + value);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    private void downLoadFileByOutputStream(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        ServletContext context = getServletContext();
//        String path = context.getRealPath("/download/http_resp.png");
//        String path = "download/http_resp.png";
//        String path = "download/http_resp.png";
        String uri = req.getRequestURI();
        String fileName = null;
        int index = uri.lastIndexOf('/');
        if(index >= 0){
            fileName = uri.substring(index + 1);
        }
        else {
            fileName = uri;
        }
        String path = prefixPath + uri;
//        resp.setHeader("content-disposition","attachment;filename=" + fileName);
        //设置content-disposition响应头控制浏览器以下载的形式打开文件
        resp.setHeader("content-disposition", "attachment;filename="+fileName);

//        resp.setContentType("image/png");

        InputStream in =new FileInputStream(path);
        int len = 0;
        byte[] buffer = new byte[1024];
        OutputStream out = resp.getOutputStream();
        while ((len = in.read(buffer)) >= 1){
            out.write(buffer,0,len);
        }
        in.close();
//        out.close();
    }
}
