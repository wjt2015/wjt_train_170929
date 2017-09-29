package wjt.common;

import com.google.common.base.Throwables;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by linux2014 on 17-6-23.
 */
public class SingletonSqlSessionFactory {
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
                    } catch (IOException e) {
                        Throwables.propagate(e);
                    }
                }
            }
        }
        return sqlSessionFactory;
    }
}
