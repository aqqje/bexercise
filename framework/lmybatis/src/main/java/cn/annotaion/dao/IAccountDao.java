package cn.annotaion.dao;


import cn.annotaion.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IAccountDao {
    @Select("select * from account")
    @Results({@Result(id = true, column = "id", property = "id", javaType = Integer.class),
            @Result(column = "id", property = "id", javaType = Integer.class),
            @Result(column = "uid", property = "uid", javaType = Integer.class),
            @Result(column = "money", property = "money", javaType = Double.class),
            @Result(column = "uid", property = "user", javaType = cn.annotaion.domain.User.class, one = @One(select = "cn.annotaion.dao.IUserDao.findById"))
    })
    List<Account> findAllAndWithUser();

    @Select("select * from account where uid = #{id}")
    List<Account> findByUid(int uid);
}
