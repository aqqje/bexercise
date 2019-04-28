package cn.annotaion.dao;

import cn.annotaion.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from user")
    @Results(id = "baseMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "address", property = "address")
    })
    List<User> findAll();


    @Select("select * from user where id = #{id}")
    @ResultMap("baseMap")
    User findById(int id);

    @SelectKey(keyProperty = "id", keyColumn = "id", before = false, resultType = Integer.class, statement = {"select last_insert_id()"})
    @Insert("insert into user (username, birthday, sex, address) values (#{username}, #{birthday}, #{sex}, #{address})")
    int save(User user);

    @Delete("delete from user where id = #{id}")
    void delete(int id);

    @Update("update user set username = #{username}, birthday = #{birthday}, sex = #{sex}, address = #{address}")
    void update(User user);

    @Select("select count(*) from user")
    int total();

    @Select("select * from user where username like #{username}")
    List<User> findByUsername(String username);

    @Select("select * from user")
    @Results(id = "baseAndWithAccountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "address", property = "address"),
            @Result(column = "id", property = "accountList", javaType = List.class, many = @Many(select = "cn.annotaion.dao.IAccountDao.findByUid")),
    })
    List<User> findAllAndWithAccount();
}
