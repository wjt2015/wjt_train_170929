package wjt.myconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wjt.interceptor.Interceptor;
import wjt.interceptor.InterceptorImpl;

/**
 * @author：jintao.wang Date: 17-7-28 Time: 下午7:49
 */
@Slf4j
@Configuration
public class MyConfigure {
    @Bean
    public Interceptor interceptor(){
        log.info("wjt;class MyConfigure;Interceptor interceptor()");
        return new InterceptorImpl();
    }
}
