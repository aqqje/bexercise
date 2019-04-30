package cn.jdbctemplate.dao.Impl;

import cn.jdbctemplate.dao.IAccountDao;
import cn.jdbctemplate.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AccountDaoImpl1 extends JdbcDaoSupport implements IAccountDao {

    @Override
    public List<Account> findAll() {
        return super.getJdbcTemplate().query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
    }

    @Override
    public Account findById(int id) {
        String sql = "select * from account where id = ?";
        return super.getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<Account>(Account.class), id);
//        return jt.query(sql,new AccountMapper(), id).get(0);
    }

    @Override
    public void delete(int id) {
        super.getJdbcTemplate().update("delete from account where id = ?", id);
    }

    @Override
    public void save(Account account) {
        super.getJdbcTemplate().update("insert into account (uid, money) values (?,?)", account.getUid(), account.getMoney());
    }

    @Override
    public void update(Account account) {
        super.getJdbcTemplate().update("update account set money = ? where id = ?", account.getMoney(), account.getId());
    }

    @Override
    public int findAlldis() {
        return super.getJdbcTemplate().queryForObject("select count(*) from account", Integer.class);
    }

    /**
     * account 封装策略 不推荐
     */
    class AccountMapper implements RowMapper<Account>{

        /**
         * 将 resultSet 封装到 account 中(我们的活), 然后将 account 加入到 list 中(spring 的活)
         * @param resultSet
         * @param i
         * @return
         * @throws SQLException
         */
        @Override
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            Account account = new Account();
            account.setId(resultSet.getInt("id"));
            account.setUid(resultSet.getInt("uid"));
            account.setMoney(resultSet.getDouble("money"));
            return account;
        }
    }
}
