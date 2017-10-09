/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.dao;

import javaweb.model.LoginUserModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jintao.wang  Date: 17-10-6 Time: 下午6:52
 */
@Repository
public interface LoginUserDao {

    LoginUserModel selectLoginUserModelById(@Param("id")Integer id);

    LoginUserModel selectLoginUserModelByNameAndPassword(@Param("userName")String userName,@Param("password")String password);

    int updateLoginUserModelById(@Param("loginUserModel") LoginUserModel loginUserModel);

    int insertLoginUserModel(@Param("loginUserModel")LoginUserModel loginUserModel);
}


    