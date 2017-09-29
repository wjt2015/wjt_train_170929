package jsoup;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author：jintao.wang Date: 17-8-27 Time: 下午9:39
 */
public class MyJsoupTest {

    @Test
    public void testParseByString() {
        String htmlStr = "<html><head><title> 这里是字符串内容</title></head" + ">" + "<body><p class='p1'> 这里是 jsoup 作用的相关演示</p></body></html>";
        MyJsoup.ParseByString(htmlStr);
        System.out.println("\tvoid testParseByString()");
    }

}

