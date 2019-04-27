package cn.dynamicsql.dao;

import cn.dynamicsql.domain.User;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface IUserDao {

    User findByCondition(User user);

    User findById(Integer id);

    List<User> findBylis(int[] lis);

    /**
     * 查询所有用户包含名下的所有帐号
     * @return
     */
    List<User> findAllwithAccount();

}
