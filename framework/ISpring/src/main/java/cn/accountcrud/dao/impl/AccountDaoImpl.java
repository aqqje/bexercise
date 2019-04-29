package cn.accountcrud.dao.impl;

import cn.accountcrud.dao.IAccountDao;
import cn.accountcrud.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private QueryRunner qr;

    @Override
    public List<Account> findAll() {
        List<Account> list = null;
        try {
            list = qr.query("select * from account", new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(Account account) {
        try {
            qr.update("insert into account (uid, money) values (?, ?)", account.getUid(), account.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            qr.update("delete * from account where id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Account account) {
        try {
            qr.update("update account set money = ? where id = ?", account.getMoney(), account.getId()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
