package c2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author：jintao.wang Date: 17-8-9 Time: 下午9:59
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=CDPlayerConfig.class)
public class CDPlayerConfigTest {
//    @Autowired
    private CompactDisc cd;
    @Test
    public void testCompactDisc(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("c2/c2_spring.xml");
        cd = ctx.getBean("sgtPeppers",CompactDisc.class);
        System.out.println("cd=" + cd);
        cd.play();
    }
}

