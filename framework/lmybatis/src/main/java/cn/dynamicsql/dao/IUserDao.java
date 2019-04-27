package cn.dynamicsql.dao;

import cn.dynamicsql.domain.User;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface IUserDao {

    User findByCondition(User user);

    User findById(Integer id);

    List<User> findBylis(int[] lis);
}
