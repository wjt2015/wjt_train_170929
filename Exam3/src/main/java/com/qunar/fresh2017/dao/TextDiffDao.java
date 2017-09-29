package com.qunar.fresh2017.dao;

import com.qunar.fresh2017.mapper.TextDiffMapper;
import com.qunar.fresh2017.model.TextDiff;
import com.qunar.fresh2017.common.SingletonSqlSessionFactory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by linux2014 on 17-6-13.
 */
public class TextDiffDao {
    private SqlSessionFactory sqlSessionFactory;

    public TextDiffDao() {
        sqlSessionFactory = SingletonSqlSessionFactory.getSqlSessionFactory();
    }

    public int insertTextDiff(TextDiff textDiff){
        int iret = 0;
        SqlSession sqlSession = null;
        TextDiffMapper textDiffMapper = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            textDiffMapper = sqlSession.getMapper(TextDiffMapper.class);
            iret = textDiffMapper.insertTextDiff(textDiff);
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return iret;
    }
    public List<TextDiff> selectTextDiff(){
        List<TextDiff> textDiffList = null;
        SqlSession sqlSession = null;
        TextDiffMapper textDiffMapper = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            textDiffMapper = sqlSession.getMapper(TextDiffMapper.class);
            textDiffList = textDiffMapper.selectTextDiff();
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return textDiffList;
    }
    /*查看最近的n条对比记录*/
    public List<TextDiff> selectTextDiffLatest(Integer n){
        List<TextDiff> textDiffList = null;
        SqlSession sqlSession = null;
        TextDiffMapper textDiffMapper = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            textDiffMapper = sqlSession.getMapper(TextDiffMapper.class);
            textDiffList = textDiffMapper.selectTextDiffLatest(n);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return textDiffList;
    }
    /*查看最近的记录，按时间逆序排序，从startIndex开始，记录个数为n*/
    public List<TextDiff> selectTextDiffRangeByDate(Integer startIndex,Integer n){
        List<TextDiff> textDiffList = null;
        SqlSession sqlSession = null;
        TextDiffMapper textDiffMapper = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            textDiffMapper = sqlSession.getMapper(TextDiffMapper.class);
            textDiffList = textDiffMapper.selectTextDiffRangeByDate(startIndex,n);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return textDiffList;
    }
    public int deleteTextDiffById(Integer id){
        int iret = 0;
        SqlSession sqlSession = null;
        TextDiffMapper textDiffMapper = null;
        try {
            sqlSession = sqlSessionFactory.openSession(true);
            textDiffMapper = sqlSession.getMapper(TextDiffMapper.class);
            iret = textDiffMapper.deleteTextDiffById(id);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return iret;
    }
}
