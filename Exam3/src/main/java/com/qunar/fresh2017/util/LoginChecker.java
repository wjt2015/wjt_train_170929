package com.qunar.fresh2017.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by linux2014 on 17-6-19.
 */
/*用于检查是否成功登录*/
public class LoginChecker {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginChecker.class);
    /*namePwd存储用户名和密码*/
    private Map<String,String> namePwd;

    public LoginChecker(String accountFname) {
        if(accountFname != null){
            namePwd = new HashMap<String, String>();
            BufferedReader reader = null;
            Pattern pattern = Pattern.compile("=");
            String strLine = null;
            try {
                reader = new BufferedReader(new FileReader(accountFname));
                while((strLine = reader.readLine()) != null){
                    String[] account = pattern.split(strLine);
                    if(account.length == 2){
                        namePwd.put(account[0],account[1]);
                    }
                }
            } catch (FileNotFoundException e) {
                LOGGER.error("FileNotFoundException happens!!",e);
            } catch (IOException ioe){
                LOGGER.error("IOException happens!!",ioe);
            } finally {
                if(reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        LOGGER.error("FileNotFoundException happens!!",e);
                    }
                }
            }
        }
    }
    /*检查能否成功登录*/
    public boolean isLogin(String name,String password){
        boolean bret = false;
        if(name != null && password != null && namePwd != null){
            if(namePwd.get(name).equals(password)){
                bret = true;
            }
        }
        return bret;
    }
}
