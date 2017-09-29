package com.qunar.fresh2017.support;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理cookie相关操作
 *
 * @author lihuiminli
 * @create 2017-06-22 下午2:13
 */
@Slf4j
public class CookieUtil {
    private static final String COOKIE_NAME = "userId";

    /**
     * 通过cookie获取登录用户的userId
     *
     * @param request
     * @return
     */
    public static String getUserId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies || 0 == cookies.length) {
            log.info("login:未获取到cookie信息！");
            return "";
        }
        for (Cookie cookie : cookies) {
            if (COOKIE_NAME.equals(cookie.getName())) {
                return AESUtil.decrypt(cookie.getValue());
            }
        }
        return "";
    }

    /**
     * 增加登录cookie
     *
     * @param response
     * @param userRoleNum
     */
    public static void addCookie(HttpServletResponse response, String userRoleNum) {
        userRoleNum = AESUtil.encrypt(userRoleNum);
        Cookie cookie = new Cookie(COOKIE_NAME, userRoleNum);
        cookie.setMaxAge(60 * 60);
        cookie.setDomain(".qunar.com");
        cookie.setPath("/");
        response.addCookie(cookie);
    }


}
