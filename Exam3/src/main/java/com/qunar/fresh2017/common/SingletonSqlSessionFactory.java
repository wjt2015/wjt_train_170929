package com.qunar.fresh2017.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by linux2014 on 17-6-13.
 */
public class SingletonSqlSessionFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(SingletonSqlSessionFactory.class);

    private static volatile SqlSessionFactory sqlSessionFactory = null;

    private SingletonSqlSessionFactory() {
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            synchronized (SingletonSqlSessionFactory.class) {
                if (sqlSessionFactory == null) {
                    try {
                        Reader resourceAsReader = Resources.getResourceAsReader("mybatis-config.xml");
                        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
                        LOGGER.info("\tsqlSessionFactory:" + sqlSessionFactory);
                    } catch (IOException e) {
                        LOGGER.error("IOException happens!!",e);
                    }
                }
            }
        }
        return sqlSessionFactory;
    }
}
