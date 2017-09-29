/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.ctx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 用单例模式实现spring容器
 * @author jintao.wang  Date: 17-9-19 Time: 上午11:07
 */
public class SingleApplicationContext {
    private static ApplicationContext ctx;


    public static ApplicationContext getInstance(){
        if(ctx == null){
            synchronized (SingleApplicationContext.class){
                if(ctx == null){
                    ctx = new ClassPathXmlApplicationContext();
                }
            }
        }
        return ctx;
    }
}
    