package cn.xmlcustom.dao;

import cn.xmlcustom.domain.User;

import java.util.List;

public interface IUserDao {

    List<User> findAll();
}
