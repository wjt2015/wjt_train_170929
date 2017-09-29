/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.listener;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Enumeration;

/**
 * @author jintao.wang  Date: 17-9-27 Time: 下午5:56
 */
public class MyServletContextListener extends ContextLoader implements ServletContextListener {

    private WebApplicationContext wac;

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("\tcontextInitialized() start!!");
        ServletContext servletContext = sce.getServletContext();
        Object source = sce.getSource();
        Enumeration<String> stringEnumeration = servletContext.getInitParameterNames();
        String paraName = null,paraValue = null;
        while (stringEnumeration.hasMoreElements()){
            paraName = stringEnumeration.nextElement();
            paraValue = servletContext.getInitParameter(paraName);
            System.out.println("\tparaName=" + paraName + ";paraValue=" + paraValue);
        }

        this.wac = initWebApplicationContext(servletContext);

        System.out.println("\tcontextInitialized() finish!!");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\tcontextDestroyed() start!!");
        ServletContext servletContext = sce.getServletContext();
        Object source = sce.getSource();

        this.wac = null;

        System.out.println("\tcontextDestroyed() finish!!");
    }
}
    