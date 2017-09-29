/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.utils;

import javaweb.dao.EmployeeListOndutyDao;

/**
 * @author jintao.wang  Date: 17-9-15 Time: 下午7:04
 */
public class BridgeUtil {

    private static EmployeeListOndutyDao employeeListOndutyDao;

    public static EmployeeListOndutyDao getEmployeeListOndutyDao() {
        return employeeListOndutyDao;
    }

    public static void setEmployeeListOndutyDao(EmployeeListOndutyDao employeeListOndutyDao) {
        if (employeeListOndutyDao != null) {
            employeeListOndutyDao = employeeListOndutyDao;
        }
    }
}


