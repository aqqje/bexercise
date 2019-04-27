package cn.xmlcrud.dao;

import cn.xmlcrud.domain.User;


public interface IUserDao {

    User findById(int id);
}
