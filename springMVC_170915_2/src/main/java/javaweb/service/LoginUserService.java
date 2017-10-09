/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.service;

import javaweb.model.LoginUserModel;


/**
 * @author jintao.wang Date: 17-10-6 Time: 下午8:48
 */
public interface LoginUserService {
    /*错误码*/
    int NO_USER = -1;
    int DATA_IS_NULL = -2;

    LoginUserModel selectLoginUserModelById(Integer id);

    LoginUserModel selectLoginUserModelByNameAndPassword(String userName,String password);

    int updateLoginUserModelById(LoginUserModel loginUserModel);

    int insertLoginUserModel(LoginUserModel loginUserModel);

    int updateLogStateByUserNameAndPassword(String userName,String password,Byte newLogState);

    int updateLoginUserModelByUserNameAndPassword(LoginUserModel loginUserModel);
}
