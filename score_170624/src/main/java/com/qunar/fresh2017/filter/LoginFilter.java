package com.qunar.fresh2017.filter;

import com.qunar.fresh2017.support.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lihuiminli
 * @create 2017-06-23 下午7:10
 */
@Slf4j
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("filter执行了！");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(!isCookieLogin(request)){
//            response.sendRedirect("/login");
            response.sendRedirect("/score_tomcat/login");
            return;
        }
        String userId = CookieUtil.getUserId(request);
        if(!"yaya.zhang".equals(userId)){
//            response.sendRedirect("/noPrivilege");
            response.sendRedirect("/score_tomcat/noPrivilege");
        }
        log.info("lhm;filter");
        filterChain.doFilter(request, response);
    }

    /**
     * 通过cookie判断是否登录
     *
     * @param request
     * @return
     */
    private boolean isCookieLogin(HttpServletRequest request) {
        String userId = CookieUtil.getUserId(request);
        if (StringUtils.isNotBlank(userId)) {
            log.info("您已经登录");
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
