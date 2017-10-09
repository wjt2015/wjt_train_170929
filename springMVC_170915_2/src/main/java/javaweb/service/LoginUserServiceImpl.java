/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.service;

import javaweb.dao.LoginUserDao;
import javaweb.model.LoginUserModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author jintao.wang Date: 17-10-6 Time: 下午8:49
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Resource
    private LoginUserDao loginUserDao;

    public LoginUserModel selectLoginUserModelById(Integer id) {
        if (id != null) {
            return loginUserDao.selectLoginUserModelById(id);
        }
        return null;
    }

    public LoginUserModel selectLoginUserModelByNameAndPassword(String userName, String password) {
        return loginUserDao.selectLoginUserModelByNameAndPassword(userName, password);
    }

    public int updateLoginUserModelById(LoginUserModel loginUserModel) {
        if (loginUserModel != null && loginUserModel.getId() != null) {
            return loginUserDao.updateLoginUserModelById(loginUserModel);
        }
        return -1;
    }

    public int insertLoginUserModel(LoginUserModel loginUserModel) {
        if (loginUserModel != null) {
            Integer id = loginUserModel.getId();
            String userName = loginUserModel.getUserName();
            String password = loginUserModel.getPassword();
            Long loginTime = loginUserModel.getLoginTime();
            Byte role = loginUserModel.getRole();
            Byte isLogin = loginUserModel.getIsLogin();
            if (id != null && userName != null && password != null && loginTime != null && role != null
                    && isLogin != null) {
                return loginUserDao.insertLoginUserModel(loginUserModel);
            }
        }
        return -1;
    }

    @Transactional
    public int updateLogStateByUserNameAndPassword(String userName, String password, Byte newLogState) {
        if (userName == null || password == null || newLogState == null) {
            return LoginUserService.DATA_IS_NULL;
        }

        LoginUserModel loginUserModel = loginUserDao.selectLoginUserModelByNameAndPassword(userName, password);
        if (loginUserModel == null) {
            return LoginUserService.NO_USER;
        }

        loginUserModel.setIsLogin(newLogState);
        return loginUserDao.updateLoginUserModelById(loginUserModel);
    }

    @Transactional
    public int updateLoginUserModelByUserNameAndPassword(LoginUserModel loginUserModel) {
        if (loginUserModel == null) {
            return LoginUserService.DATA_IS_NULL;
        }
        String userName = loginUserModel.getUserName(), password = loginUserModel.getPassword();
        if (userName == null || password == null){
            return LoginUserService.DATA_IS_NULL;
        }

        LoginUserModel loginUserModelInDB = loginUserDao.selectLoginUserModelByNameAndPassword(userName, password);

        if (loginUserModelInDB == null) {
            return LoginUserService.NO_USER;
        }
        /* 更新用户数据 */
        if (loginUserModel.getIsLogin() != null) {
            loginUserModelInDB.setIsLogin(loginUserModel.getIsLogin());
        }

        if (loginUserModel.getLoginTime() != null) {
            loginUserModelInDB.setLoginTime(loginUserModel.getLoginTime());
        }

        if (loginUserModel.getRole() != null) {
            loginUserModelInDB.setRole(loginUserModel.getRole());
        }
        /* 将更新的结果写入数据库 */
        return loginUserDao.updateLoginUserModelById(loginUserModelInDB);
    }

}
