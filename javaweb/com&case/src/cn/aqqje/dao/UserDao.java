package cn.aqqje.dao;

import cn.aqqje.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    /*查询所有用户*/
    List<User> findAll();

    /*根据用户名和密码查询用户*/
    User findUserByUsernameAndPassword(String username, String password);

    /*添加一个新用户*/
    Boolean addUser(User user);

    /*更新用户*/
    Boolean updateUser(User user);

    /*根据用户ID删除用户*/
    Boolean deleteUserById(int id);

    /*根据用户id查询用户*/
    User findOneById(int id);

    /*总记录数*/
    int totalCount(Map<String, String[]> condation);

    List<User> beanPageList(int start, int rows, Map<String, String[]> condation);
}
