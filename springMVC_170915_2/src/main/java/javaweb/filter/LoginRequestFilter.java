/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.filter;

import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author jintao.wang  Date: 17-9-26 Time: 下午8:58
 */
@Slf4j
public class LoginRequestFilter implements Filter {

    private List<String> loginPageStringList;

    public void init(FilterConfig filterConfig) throws ServletException {

/*        String fname = "/home/linux2014/WJT_2017/projects/idea_projects/wjt_train_170622/springMVC_170915_2/src/main/webapp/pages/login.html";
        try {
            Charset charset = Charset.defaultCharset();
            loginPageStringList = Files.readLines(new File(fname), charset);
        } catch (IOException e) {
            log.error("init()--read() error!!",e);
        }*/
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

/*        PrintWriter printWriter = httpServletResponse.getWriter();
        for (String strLine:loginPageStringList){
            printWriter.write(strLine + "\n");
        }*/
/*        httpServletResponse.setContentType("text/html;charset=UTF-8");*/
        httpServletResponse.sendRedirect("pages/login.html");
        System.out.println("doFilter() finish!!");
    }

    public void destroy() {
        loginPageStringList = null;
    }
}
    