/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.service.impl;

import com.google.common.base.Preconditions;
import javaweb.dao.AuthDao;
import javaweb.model.AuthModel;
import javaweb.service.AuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jintao.wang  Date: 17-9-28 Time: 下午2:08
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private AuthDao authDao;

    public int insertAuthModel(AuthModel authModel) {
        Preconditions.checkNotNull(authModel);
        Preconditions.checkNotNull(authModel.getUserName());
        Preconditions.checkNotNull(authModel.getPassword());
        Preconditions.checkNotNull(authModel.getRole());

        return authDao.insertAuthModel(authModel.getUserName(),authModel.getPassword(),authModel.getRole());
    }

    public int deleteAuthModelById(Integer id) {
        Preconditions.checkNotNull(id);
        return authDao.deleteAuthModelById(id);
    }

    public AuthModel selectAuthModelById(Integer id) {
        Preconditions.checkNotNull(id);
        return authDao.selectAuthModelById(id);
    }

    public int updateAuthModelById(AuthModel authModel) {
        Preconditions.checkNotNull(authModel);
        Preconditions.checkNotNull(authModel.getId());
        return authDao.updateAuthModelById(authModel.getId(),authModel.getUserName(),authModel.getPassword(),authModel.getRole());
    }
}


