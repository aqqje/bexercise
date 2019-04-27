package cn.xmlcustom.dao;

import cn.xmlcustom.domain.User;
import cn.xmlcustom.mybaties.annotation.Select;

import java.util.List;

public interface IUserDao {

    @Select("select * from user")
    List<User> findAll();
}
