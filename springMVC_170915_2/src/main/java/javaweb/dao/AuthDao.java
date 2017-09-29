/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.dao;

import javaweb.model.AuthModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jintao.wang  Date: 17-9-28 Time: 下午1:41
 */
@Repository
public interface AuthDao {

    int insertAuthModel(@Param("userName")String userName,@Param("password")String password,@Param("role")Byte role);

    int deleteAuthModelById(@Param("id") Integer id);

    AuthModel selectAuthModelById(@Param("id") Integer id);

    int updateAuthModelById(@Param("id") Integer id,@Param("userName")String userName,@Param("password")String password,@Param("role")Byte role);

}

    