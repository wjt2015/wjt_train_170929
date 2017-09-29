package com.qunar.fresh2017.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by linux2014 on 17-7-3.
 */
@Slf4j
public class WJTFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){
        log.info("\twjt;filter;init()");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        log.info("doFilter(ServletRequest request, ServletResponse response, FilterChain chain);");
        String uriStr = httpServletRequest.getRequestURI();
        log.info("wjt;filter;request－uriStr：" + uriStr);
        /*读取request,cookie的内容*/
        Cookie[] cookieArr = httpServletRequest.getCookies();
        if(cookieArr != null){
            for (Cookie cookie:cookieArr){
                log.info("cookie={}",cookie);
            }
        }
        log.info("doFilter();---------");
        try {
            /**/
//            printContextOfRequest(httpServletRequest);
            /**/
            chain.doFilter(request,response);
        } catch (IOException e) {
            log.error("IOException happens!!",e);
        } catch (ServletException e) {
            log.error("ServletException happens!!",e);
        }
    }
    @Override
    public void destroy(){
        log.info("\twjt;filter;destroy()");
    }

    private void printContextOfRequest(HttpServletRequest httpServletRequest) throws IOException {
        if(httpServletRequest != null){
            BufferedReader bufferedReader = httpServletRequest.getReader();
            String strLine = null;
            while((strLine = bufferedReader.readLine()) != null){
                log.info(strLine);
            }
//            bufferedReader.close();
            log.info("wjt;printContextOfRequest()");
        }
    }

    private void printContextOfReponse(HttpServletResponse httpServletResponse) throws IOException {
        if(httpServletResponse != null){
            PrintWriter printWriter = httpServletResponse.getWriter();
        }
    }
}


