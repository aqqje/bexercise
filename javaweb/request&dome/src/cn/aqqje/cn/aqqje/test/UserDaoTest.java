package cn.aqqje.cn.aqqje.test;

import cn.aqqje.cn.aqqje.dao.UserDao;
import cn.aqqje.cn.aqqje.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void loginTest(){
        UserDao dao = new UserDao();
        User user = new User("aqqje","12345");
        System.out.println(user);
        User login = dao.login(user);
        System.out.println(login);
    }
}
