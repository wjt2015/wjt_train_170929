package javaweb.dao;

import javaweb.model.LoginUserModel;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * @author jintao.wang Date: 17-10-6 Time: 下午7:56
 */
public class LoginUserDaoTest {

    private LoginUserDao loginUserDao;

    @Before
    public void init(){
        String fname = "spring_context.xml";

        ApplicationContext ctx = new ClassPathXmlApplicationContext(fname);

        loginUserDao = ctx.getBean(LoginUserDao.class);
    }

    @Test
    public void selectLoginUserModelById() throws Exception {

    }

    @Test
    public void updateLoginUserModelById() throws Exception {
    }

    @Test
    public void testLoginUserDao() throws Exception {
        Byte role = new Byte((byte)1);
        Byte isLogin = new Byte((byte)0);
        LoginUserModel loginUserModel = new LoginUserModel(null,"李自成","明末农民军领袖",new DateTime().getMillis(),role,isLogin);
        int iret = loginUserDao.insertLoginUserModel(loginUserModel);
        System.out.println("\tiret = " + iret);

        Integer id = 6;
        loginUserModel = loginUserDao.selectLoginUserModelById(id);
        System.out.println("id =" + id + ";loginUserModel=" + loginUserModel);
        id = 7;
        loginUserModel = loginUserDao.selectLoginUserModelById(id);
        System.out.println("id =" + id + ";loginUserModel=" + loginUserModel);

        JdbcType jdbcType;
    }

}


