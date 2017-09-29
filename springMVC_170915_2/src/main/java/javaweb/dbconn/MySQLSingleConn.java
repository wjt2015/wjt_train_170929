/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.dbconn;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 单例模式
 * 
 * @author jintao.wang Date: 17-9-19 Time: 下午5:36
 */
@Slf4j
public class MySQLSingleConn {
    private static Connection connection;

    public synchronized static Connection getInstance() {
        if (connection == null) {
            String connectionURL = "jdbc:mysql://localhost:3306/crm_statistic?userUnicode=true&amp;characterEncoding=utf8";
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                log.error("ClassNotFoundException!!", e);
            }
            try {
                connection = DriverManager.getConnection(connectionURL, "root", "linux2014");
            } catch (SQLException e) {
                log.error("SQLException!!", e);
            }
        }
        return connection;
    }
}
