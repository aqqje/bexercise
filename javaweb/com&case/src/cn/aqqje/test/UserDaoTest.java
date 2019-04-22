package cn.aqqje.test;

import cn.aqqje.dao.UserDao;
import cn.aqqje.dao.impl.UserDaoImpl;
import cn.aqqje.domain.User;
import org.junit.Test;

public class UserDaoTest {

    private UserDao userDao = new UserDaoImpl();

    @Test
    public void testFindAll(){
        for (User user:userDao.findAll()) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindUserByUsernameAndPassword(){
        System.out.println(userDao.findUserByUsernameAndPassword("aqq11je", "12345"));
    }

    @Test
    public void testAddUser(){
        User user = new User("ouming", "女", 12, "中国", "1042136232", "1042136232@qq.com", null, null);
        System.out.println(userDao.addUser(user));
    }

    @Test
    public void testUpdateUser(){
        User user = new User(5,"ouming", "男", 12, "中国", "1042136232", "1042136232@qq.com", null, null);
        System.out.println(userDao.updateUser(user));
    }

    @Test
    public void testDeleteUserById(){
        System.out.println(userDao.deleteUserById(5));
    }

    @Test
    public void testFindOneById(){
        System.out.println(userDao.findOneById(3));
    }

    @Test
    public void testTotalCount(){
        /*System.out.println(userDao.totalCount(condation));*/
    }

    @Test
    public void testBeanPageList(){
        /*System.out.println(userDao.beanPageList(1,4, condation));*/
    }
}