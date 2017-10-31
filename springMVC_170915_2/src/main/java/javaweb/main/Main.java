/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.main;

import com.qunar.scm.common.utils.JsonUtil;
import javaweb.model.LoginUserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.joda.time.DateTime;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jintao.wang  Date: 17-9-18 Time: 下午4:43
 */
@Slf4j
public class Main {

    public static void main(String[] args){
/*        try {
           *//* jsonFunc();*//*
            jsoupTest();
        } catch (IOException e) {
            log.error("IOException happens!!",e);
        }*/
/*        jedisTest();*/
        testCookieDomain();
    }
    public static void func(){
        HttpServletRequest httpServletRequest = null;
        HttpServletResponse httpServletResponse = null;
        Cookie cookie;
        DateTime dt = new DateTime();

    }
    private static void md5(){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException",e);
        }
        String str = "ABCDEFG";
        byte[] buff = str.getBytes();
        messageDigest.update(buff);
        buff = messageDigest.digest();
        System.out.println("\tbuff=" + buff);
    }

    private static void jsonFunc() throws IOException {
        LoginUserModel loginUserModel = new LoginUserModel(5,"clinton","xilali",123242L,(byte)1,(byte)1);
        String userInfo = JsonUtil.encode(loginUserModel);
        System.out.println("userInfo=" + userInfo);
        loginUserModel = new LoginUserModel(15,"ABC","xilali",123242L,(byte)1,(byte)1);
        loginUserModel = JsonUtil.decode(userInfo,LoginUserModel.class);
        System.out.println("loginUserModel=" + loginUserModel);



        /**/
        HandlerMapping handlerMapping;
        HandlerAdapter handlerAdapter;
        DispatcherServlet dispatcherServlet;
        InternalResourceViewResolver internalResourceViewResolver;
        BeanNameUrlHandlerMapping beanNameUrlHandlerMapping;

        XmlWebApplicationContext xmlWebApplicationContext;
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext;

        Jsoup jsoup;
        HttpClient httpClient;
        HttpSession httpSession;
        Cookie cookie;
    }

    private static void testCookieDomain(){
        String[] requestDomainArr = {"zyday.com","blog.zyday.com","one.blog.zyday.com"};
        String[] cookieDomainArr = {"","zyday.com","blog.zyday.com","one.blog.zyday.com"};
        for (String requestDomain:requestDomainArr){
            System.out.println("\t------;\nrequestDomain=" + requestDomain);
            for (String cookieDomain:cookieDomainArr){
                boolean bret = requestDomain.endsWith(cookieDomain);
                System.out.println("\tcookieDomain=" + cookieDomain + ";bret=" + bret);
            }
        }
    }
    private static void jsoupTest() throws IOException {
        String url = "http://www.iteye.com/blogs/subjects";
        Connection connection = Jsoup.connect(url);
        Document document = connection.get();
        Elements elements = document.getElementsByTag("a");
        for (Element element:elements){
            String href = element.attr("href");
            String text = element.text();
            System.out.println("href=" + href + ";text=" + text);
        }

    }

    private static void jedisTest(){
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());

        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));

    }
}


