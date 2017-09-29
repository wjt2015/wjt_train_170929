/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.servlet;

import javaweb.dao.EmployeeListOndutyDao;
import javaweb.utils.BridgeUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jintao.wang  Date: 17-9-15 Time: 下午6:44
 */
public class BridgeService implements InitializingBean, ApplicationListener<ContextRefreshedEvent> {

    private ApplicationContext applicationContext;

    @Resource private EmployeeListOndutyDao employeeListOndutyDao;

    public void afterPropertiesSet() throws Exception {
        BridgeUtil.setEmployeeListOndutyDao(employeeListOndutyDao);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        EmployeeListOndutyDao employeeListOndutyDao = (EmployeeListOndutyDao) applicationContext
                .getBean("employeeListOndutyDao");
        BridgeUtil.setEmployeeListOndutyDao(employeeListOndutyDao);
    }
}
