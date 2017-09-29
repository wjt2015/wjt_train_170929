package wjt.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import wjt.controller.HelloReaderController;

import static org.junit.Assert.*;

/**
 * @author：jintao.wang Date: 17-7-27 Time: 下午7:21
 */
@Slf4j
public class InterceptorTest {
    @Test
    public void testInterceptor(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop/aop.xml");
        Interceptor interceptor = (Interceptor)ctx.getBean("interceptorID1");
        HelloReaderController helloReaderController = new HelloReaderController();
        ModelAndView mv = helloReaderController.getHello();
        log.info("class InterceptorTest;void testInterceptor()");
    }
}

