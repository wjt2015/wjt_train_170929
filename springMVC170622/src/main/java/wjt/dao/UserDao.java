package wjt.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import wjt.common.SingletonSqlSessionFactory;
import wjt.mapper.UserMapper;
import wjt.model.User;

/**
 * Created by linux2014 on 17-6-23.
 */
@Repository
public class UserDao {
    private SqlSessionFactory sqlSessionFactory;

    public UserDao() {
        sqlSessionFactory = SingletonSqlSessionFactory.getSqlSessionFactory();
    }
    public int insertUser(User user){
        SqlSession sqlSession = null;
        UserMapper userMapper = null;
        int iret = 0;
        try {
            sqlSession = sqlSessionFactory.openSession(true);
            userMapper = sqlSession.getMapper(UserMapper.class);
            iret = userMapper.insertUser(user);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return iret;
    }
}



