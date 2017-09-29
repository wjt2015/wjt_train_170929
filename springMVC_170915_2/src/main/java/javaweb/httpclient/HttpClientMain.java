/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.params.HttpParams;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author jintao.wang  Date: 17-9-29 Time: 下午6:26
 */
@Slf4j
public class HttpClientMain {
    public static void main(String[] args){
        String url = "http://crm.qunarman.com";
        func(url);
    }


    private static void func(String url){
        HttpClient httpClient = new HttpClient();
//        String url = "http:www.qunarman.com";
        GetMethod getMethod = new GetMethod(url);
        HttpMethodParams httpMethodParams = getMethod.getParams();
        httpMethodParams.setParameter("name","jintao.wang");
        httpMethodParams.setParameter("pwd","Qunar.201351");
        httpMethodParams.setIntParameter("use_time",1000);
        getMethod.setParams(httpMethodParams);
        int status = 0;
        byte[] responseBuf = null;
        InputStream inputStream = null;
        String responseString = null;
        try {
            status = httpClient.executeMethod(getMethod);
            inputStream = getMethod.getResponseBodyAsStream();
            responseString = getMethod.getResponseBodyAsString();
            Header[] responseHeaderArr = getMethod.getResponseHeaders();
            if(responseHeaderArr != null){
                for (Header header:responseHeaderArr){
                    System.out.println("\theader:" + header);
                }
            }
            System.out.println("\tresponseString=" + responseString);
        } catch (IOException e) {
            log.error("httpClient execute error!!",e);
        }
        finally {
            if(getMethod != null){
                getMethod.releaseConnection();
            }
        }
        System.out.println("func() finish!!");
    }
}
    