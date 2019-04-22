package cn.aqqje.cn.aqqje.dao;

import cn.aqqje.cn.aqqje.domain.User;
import cn.aqqje.cn.aqqje.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 操作数据库的 User 表
 */
public class UserDao {

    // 声明 JdbcTemplate 共用对象
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    public User login(User loginUser){
        try {
            // 执行 sql 并返回结果
            return jdbcTemplate.queryForObject("select * from user where name = ? and password = ?",
                    new BeanPropertyRowMapper<User>(User.class), loginUser.getName(), loginUser.getPassword());
        }catch(DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
