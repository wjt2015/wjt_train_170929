package wjt.mapper;

import org.apache.ibatis.annotations.Param;
import wjt.model.User;

import javax.jws.soap.SOAPBinding;

/**
 * Created by linux2014 on 17-6-23.
 */
public interface UserMapper {
    int insertUser(@Param("user")User user);
}
