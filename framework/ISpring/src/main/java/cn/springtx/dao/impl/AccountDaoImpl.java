package cn.springtx.dao.impl;




import cn.springtx.dao.IAccountDao;
import cn.springtx.domain.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class AccountDaoImpl extends JdbcDaoSupport implements IAccountDao {

    @Override
    public Account findById(int id) {
        Account account = null;
        try {
             account = super.getJdbcTemplate().queryForObject("select * from account where id = ?", new BeanPropertyRowMapper<Account>(Account.class), id);
        } catch (DataAccessException e) {
        }
        return account;
    }

    @Override
    public boolean update(Account account) {
        return super.getJdbcTemplate().update("update account set money = ? where id = ?", account.getMoney(), account.getId()) == 1 ? true : false;
    }
}
