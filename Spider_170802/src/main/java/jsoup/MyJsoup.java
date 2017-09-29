package jsoup;

import com.google.common.base.Preconditions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author：jintao.wang Date: 17-8-27 Time: 下午9:16
 */
public class MyJsoup {
    public static void ParseByString(String htmlStr){
        Preconditions.checkNotNull(htmlStr);
        Document document = Jsoup.parse(htmlStr);
        Elements links = document.select("p[class]");
        for(Element link:links){
            String linkText = link.text();
            String linkClsName = link.className();
            System.out.println("linkText = " + linkText + ";linkClsName = " + linkClsName);
        }
    }
}
