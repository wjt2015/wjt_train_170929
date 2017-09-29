package wjt.interceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author：jintao.wang Date: 17-7-27 Time: 下午7:04
 */
@Slf4j
public class InterceptorImpl implements Interceptor {
    public void performBefore() {
        System.out.println("wjt;InterceptorImpl; void perfromBefore()");
        log.info("log;wjt;InterceptorImpl; void performBefore()");
    }
    public void performAfter(){
        System.out.println("wjt;InterceptorImpl; void perfromAfter()");
        log.info("log;wjt;InterceptorImpl; void performAfter()");
    }
}



