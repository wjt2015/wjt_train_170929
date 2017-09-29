package util;

import com.qunar.fresh2017.util.TextComparisionAndDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by linux2014 on 17-6-16.
 */
public class TextComparisionAndDBTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextComparisionAndDBTest.class);
    private TextComparisionAndDB textComparisionAndDB;

    @Before
    public void init(){
        textComparisionAndDB = new TextComparisionAndDB();
    }
    @After
    public void destroy(){
        textComparisionAndDB = null;
    }
    @Test
    public void func1(){
        String path = "/home/linux2014/WJT_2017/projects/idea_projects/train_projects/dev_training2/Exam3/src/main/resources/text/";
        File srcFile = new File(path + "src1.txt");
        File targetFile = new File(path + "target1.txt");
        textComparisionAndDB.comparisonAndDBFunc(srcFile,targetFile);
    }
}