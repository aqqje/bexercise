package cn.aqqje.dao.impl;

import cn.aqqje.dao.UserDao;
import cn.aqqje.domain.User;
import cn.aqqje.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Boolean addUser(User user) {
        String sql = "insert into user values (null,?,?,?,?,?,?,null,null)";
        int flag = jdbcTemplate.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
        return flag >= 1 ? true: false;
    }

    @Override
    public Boolean updateUser(User user) {
        String sql = "update user set name = ?, gender = ?, age = ?, address = ?, qq =?, email = ? where id = ?";
        int flag = jdbcTemplate.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
        return flag >= 1 ? true: false;
    }

    @Override
    public Boolean deleteUserById(int id) {
        String sql = "delete from user where id = ?";
        int flag = jdbcTemplate.update(sql, id);
        return flag >= 1 ? true: false;
    }

    @Override
    public User findOneById(int id) {
        String sql = "select * from user where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    @Override
    public int totalCount(Map<String, String[]> condation) {
        /*初始化sql*/
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuffer sb = new StringBuffer(sql);
        /*参数集合*/
        List<Object> params = new ArrayList<>();
        /*遍历map*/
        Set<String> keySet = condation.keySet();
        for (String key:keySet) {
            if(key.equals("currentPage") || key.equals("rows")){
                continue;
            }
            String value = condation.get(key)[0];
            if(value != null && !"".equals(value)){
                /*拼接sql*/
                sb.append("and "+ key +" like ? ");
                /*拼接参数*/
                params.add("%"+value+"%");
            }
        }
        return jdbcTemplate.queryForObject(sb.toString(),Integer.class, params.toArray());
    }

    @Override
    public List<User> beanPageList(int start, int rows, Map<String, String[]> condation) {
        String sql = "select * from user where 1 = 1 ";
        StringBuffer sb = new StringBuffer(sql);
        List<Object> params = new ArrayList<>();
        /*遍历map*/
        Set<String> keySet = condation.keySet();
        for (String key:keySet) {
            if(key.equals("currentPage") || key.equals("rows")){
                continue;
            }
            String value = condation.get(key)[0];
            if(value != null && !"".equals(value)){
                sb.append("and " + key + " like ? ");
                params.add("%"+value+"%");
            }
        }
        /*sql 拼接分页信息*/
        sb.append(" limit ?, ? ");
        /*拼接分页信息参数*/
        params.add(start);
        params.add(rows);
        return jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class), params.toArray());
    }
}
