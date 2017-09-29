package httpclient;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by linux2014 on 17-7-9.
 */
public class MyHttpClient {
}
/**/
@Slf4j class HttpGet extends Thread {
    @Override public void run() {
        String urlStr = "http://localhost:8080/score_tomcat/myServlet";
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            log.error("MalformedURLException happens!!", e);
        }
        URLConnection conn = null;
        InputStream inputStream = null;
        try {
            url.openConnection();
        } catch (IOException e) {
            log.error("IOException happens!!", e);
        }
        try {
            inputStream = conn.getInputStream();
        } catch (IOException e) {
            log.error("IOException happens!!", e);
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String strLine = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((strLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(strLine);
            }
        } catch (IOException e) {
            log.error("IOException happens!!", e);
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                log.error("IOException happens!!", e);
            }
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                log.error("IOException happens!!", e);
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("IOException happens!!", e);
            }
        }
    }
}

@Slf4j class HttpPost extends Thread {
    public void run() {
        String urlStr = "http://localhost:8080/score_tomcat/myServlet";
        URL url = null;
        URLConnection conn = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            log.error("MalformedURLException happens!!", e);
        }
        try {
            conn = url.openConnection();
        } catch (IOException e) {
            log.error("IOException happens!!", e);
        }

    }
}