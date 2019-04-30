package cn.transaction.dao.impl;


import cn.transaction.dao.IAccountDao;
import cn.transaction.domain.Account;
import cn.transaction.utils.ConnectionUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    @Qualifier("qr")
    private QueryRunner qr;

    @Autowired
    @Qualifier("connUtil")
    private ConnectionUtil connUtil;


    @Override
    public Account findById(int id) {
        Account account = null;
        try {
            account = qr.query(connUtil.getThreadConnection(),"select * from account where id = ?", new BeanHandler<Account>(Account.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public boolean update(int id, double money) {
        int isSuccess = 0;
        try {
            isSuccess = qr.update(connUtil.getThreadConnection(),"update account set money = ? where id = ?", money, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess == 1 ? true : false;
    }

    /**
     * 转帐
     * @param sourceId
     * @param targetId
     * @param money
     */
    @Override
    public void transfer(int sourceId, int targetId, double money) {
        Account source = findById(sourceId);
        Account target = findById(targetId);
        update(sourceId, source.getMoney() - money);
        int i = 1 / 0;
        update(targetId, target.getMoney() + money);
    }
}
