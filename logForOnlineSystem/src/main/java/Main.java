import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author：jintao.wang Date: 17-8-1 Time: 下午3:38
 *
 */
@Slf4j
public class Main {
    public static void main(String[] args){
        String src = "/home/linux2014/WJT_2017/projects/idea_projects/wjt_train_170622/logForOnlineSystem/src/main/java/src.txt";
        String path = Main.class.getResource("/").getPath();
//        String src = "src.txt";
        String dest = "/home/linux2014/WJT_2017/projects/idea_projects/wjt_train_170622/logForOnlineSystem/src/main/java/dest.txt";
        File fsrc = new File(src);
        File fdest = new File(dest);
        StringTranslator stringTranslator = new StringTranslator();
        try {
            stringTranslator.doTranslate(fsrc);
        } catch (IOException e) {
            log.error("IOException happen!",e);
        }
        String from = stringTranslator.getLineProcessor().getResult();
        try {
            Files.write(from,fdest, Charset.defaultCharset());
        } catch (IOException e) {
            log.error("IOException happen!",e);
        }
    }
}


