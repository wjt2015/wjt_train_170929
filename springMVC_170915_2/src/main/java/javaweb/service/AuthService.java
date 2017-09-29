/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.service;

import javaweb.model.AuthModel;
import org.apache.ibatis.annotations.Param;

/**
 * @author jintao.wang  Date: 17-9-28 Time: 下午1:56
 */
public interface AuthService {

    int insertAuthModel(AuthModel authModel);

    int deleteAuthModelById( Integer id);

    AuthModel selectAuthModelById( Integer id);

    int updateAuthModelById( AuthModel authModel);

}
    