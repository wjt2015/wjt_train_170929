/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jintao.wang  Date: 17-9-18 Time: 下午4:43
 */
@Slf4j
public class Main {

    public static void main(String[] args){
        md5();
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
}
    