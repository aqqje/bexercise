package cn.jdbctemplate.dao.Impl;

import cn.jdbctemplate.dao.IAccountDao;
import cn.jdbctemplate.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("accountDao1")
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private JdbcTemplate jt;

    @Override
    public List<Account> findAll() {
        return jt.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
    }

    @Override
    public Account findById(int id) {
        String sql = "select * from account where id = ?";
        return jt.queryForObject(sql, new BeanPropertyRowMapper<Account>(Account.class), id);
//        return jt.query(sql,new AccountMapper(), id).get(0);
    }

    @Override
    public void delete(int id) {
        jt.update("delete from account where id = ?", id);
    }

    @Override
    public void save(Account account) {
        jt.update("insert into account (uid, money) values (?,?)", account.getUid(), account.getMoney());
    }

    @Override
    public void update(Account account) {
        jt.update("update account set money = ? where id = ?", account.getMoney(), account.getId());
    }

    @Override
    public int findAlldis() {
        return jt.queryForObject("select count(*) from account", Integer.class);
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
