package cn.aqqje.service;

import cn.aqqje.domain.BeanPage;
import cn.aqqje.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /*用户登录*/
    User login(User loginUser);

    /*列出所有信息*/
    List<User> findAll();

    /*添加用户*/
    void addUser(User user);

    /*根据id删除用户*/
    void deleteUserById(int id);

    /*根据id查询用户*/
    User findOneById(int id);

    /*更新用户*/
    void updateUser(User updateUser);

    /*根据id删除用户*/
    void deleteUserByIds(String[] ids);

    /*分页*/
    BeanPage beanPage(int currentPage, int rows, Map<String, String[]> condation);
}
