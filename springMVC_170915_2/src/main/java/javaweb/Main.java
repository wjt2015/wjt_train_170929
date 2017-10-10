/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb;

import com.qunar.scm.common.utils.JsonUtil;
import javaweb.model.LoginUserModel;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jintao.wang  Date: 17-9-18 Time: 下午4:43
 */
@Slf4j
public class Main {

    public static void main(String[] args){
        try {
            jsonFunc();
        } catch (IOException e) {
            log.error("IOException happens!!",e);
        }
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
    }
}
    