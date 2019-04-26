package cn.introduction.dao;

import cn.introduction.domain.User;

import java.util.List;

public interface IUserDao {

    List<User> findAll();
}
