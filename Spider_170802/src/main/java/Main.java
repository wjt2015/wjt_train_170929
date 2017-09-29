import com.google.common.base.Splitter;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author：jintao.wang Date: 17-8-8 Time: 下午8:15
 */
public class Main {
    public static void main(String[] args){
        Pattern pattern = Pattern.compile("[,; \t]");
        String str = "guangyan.yao; \txinya.song;weigui.li,;;weixing.zhou;baoqiu.xiao;bo.li;";
        List<String> qtalkList = Splitter.on(pattern).trimResults().omitEmptyStrings().splitToList(str);
        System.out.println("qtalkList="+ qtalkList);
    }

}





