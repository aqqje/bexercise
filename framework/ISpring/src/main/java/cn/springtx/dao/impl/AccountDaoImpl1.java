package cn.springtx.dao.impl;




import cn.springtx.dao.IAccountDao;
import cn.springtx.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("accountDao1")
public class AccountDaoImpl1 implements IAccountDao {

    @Autowired
    private JdbcTemplate jx;

    @Override
    public Account findById(int id) {
        Account account = null;
        try {
             account = jx.queryForObject("select * from account where id = ?", new BeanPropertyRowMapper<Account>(Account.class), id);
        } catch (DataAccessException e) {
        }
        return account;
    }

    @Override
    public boolean update(Account account) {
        return jx.update("update account set money = ? where id = ?", account.getMoney(), account.getId()) == 1 ? true : false;
    }
}
